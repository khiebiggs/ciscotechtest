package app.serde;

import app.model.NodeValueResult;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class NodeValueResultSerde {
    private static Serde<NodeValueResult> nodeValueResultSerde = null;

    public static Serde<NodeValueResult> Serde(){
        if(nodeValueResultSerde == null){
            Map<String, Object> serdeProps = new HashMap<>();
            // serializer for NodeValueResult, to be utilised in SerDe
            final Serializer<NodeValueResult> nodeValueResultSerializer = new JsonPOJOSerializer<>();
            serdeProps.put("JsonPOJOClass", NodeValueResult.class);
            nodeValueResultSerializer.configure(serdeProps, false);

            // deserializer for NodeValueResult, to be utilised in SerDe
            final Deserializer<NodeValueResult> nodeValueResultDeserializer = new JsonPOJODeserializer<>();
            serdeProps.put("JsonPOJOClass", NodeValueResult.class);
            nodeValueResultDeserializer.configure(serdeProps, false);

            nodeValueResultSerde = Serdes.serdeFrom(nodeValueResultSerializer, nodeValueResultDeserializer);
        }

        return nodeValueResultSerde;
    }
}
