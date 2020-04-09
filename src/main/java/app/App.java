package app;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class App {
    public static void createTopics() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:29092");
        AdminClient client = AdminClient.create(config);

        List<NewTopic> topics = new ArrayList<>();
        topics.add(new NewTopic("node-value-input", 1, Short.parseShort("1")));
        topics.add(new NewTopic("node-average-input", 1, Short.parseShort("1")));

        client.createTopics(topics);
        client.close();
    }

    public static void main(String[] args) {
        runRecipe();
    }

    private static void runRecipe() {
        KafkaAggregator kafkaAggregator = new KafkaAggregator();
        Properties aggregatorStreamProperties = kafkaAggregator.buildStreamProperties();
        Topology topology = kafkaAggregator.buildTopology();

        createTopics();

        Thread kafkaDatabaseWriterThread = new Thread(() -> {
            try {
                KafkaDatabaseWriter.runConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final KafkaStreams aggregatorStream = new KafkaStreams(topology, aggregatorStreamProperties);
        final CountDownLatch latch = new CountDownLatch(1);

        // Attach shutdown handler to catch Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                aggregatorStream.close();
                latch.countDown();
            }
        });

        try {
            kafkaDatabaseWriterThread.start();
            aggregatorStream.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }

        System.exit(0);
    }
}
