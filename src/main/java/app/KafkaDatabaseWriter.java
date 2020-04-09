package app;

import app.DB.AverageDao;
import app.model.Average;
import app.model.NodeValueResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaDatabaseWriter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Properties buildStreamProperties(){
        Properties props = new Properties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaDatabaseWriter");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }

    private static Consumer<String, String> createConsumer(Properties props){
        final Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("node-average-input"));
        return consumer;
    }

    static void runConsumer() throws InterruptedException {
        final Consumer<String, String> consumer = createConsumer(buildStreamProperties());
        AverageDao averageDao = new AverageDao();

        try {
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                for(ConsumerRecord<String, String> record : records){
                    System.out.println(record.key() + ": " + record.value());

                    NodeValueResult nodeValueResult = objectMapper.readValue(record.value(), NodeValueResult.class);
                    Average average = new Average(Long.valueOf(record.key()),
                            nodeValueResult.window_start,
                            nodeValueResult.min_value,
                            nodeValueResult.max_value,
                            nodeValueResult.total / nodeValueResult.count);
                    averageDao.saveAverage(average);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
