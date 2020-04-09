package app;

import app.model.NodeValueResult;
import app.serde.NodeValueResultSerde;
import avro.NodeValue;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.streams.kstream.Suppressed.BufferConfig.unbounded;

public class KafkaAggregator {

    public Properties buildStreamProperties(){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-getaverage");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, NodeTimeStampExtractor.class.getName());
        return props;
    }

    public Topology buildTopology(){
        final StreamsBuilder builder = new StreamsBuilder();
        final String inputTopic = "node-value-input";
        final String outputTopic = "node-average-input";

        Map<String, Object> serdeProps = new HashMap<>();

        // get a 1 minute window for event
        TimeWindowedKStream<Long, NodeValue> windowedNodeEvent = builder.<String, NodeValue>stream(inputTopic)
                .map((key, nodeValue) -> new KeyValue<>(nodeValue.getNodeId(), nodeValue))
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(30)));   // allow 30 seconds for late messages

        // aggregate to get everything we need to calculate the average
        KTable<Windowed<Long>, NodeValueResult> aggregate = windowedNodeEvent.aggregate(() -> new NodeValueResult(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0),
                (aggKey, newValue, aggValue) -> {
                    aggValue.count++;
                    aggValue.total += newValue.getValue();
                    aggValue.max_value = Math.max(aggValue.max_value, newValue.getValue());
                    aggValue.min_value = Math.min(aggValue.min_value, newValue.getValue());
                    return aggValue;
                }, Materialized.with(Serdes.Long(), NodeValueResultSerde.Serde())).suppress(Suppressed.untilWindowCloses(unbounded())); // don't send a message for each message in, just return the aggregate of the minute

        aggregate.toStream()
                .map((Windowed<Long> key, NodeValueResult nodeValueResult) -> new KeyValue<>(String.valueOf(key.key()), nodeValueResult.decorateWindowStart(key.window().start())))
                .to(outputTopic, Produced.with(Serdes.String(), NodeValueResultSerde.Serde()));

        return builder.build();
    }
}
