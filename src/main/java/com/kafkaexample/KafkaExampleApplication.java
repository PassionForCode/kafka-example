package com.kafkaexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
    }

    /**
     * Pushes events into Kafka topic.
     * Executed automatically, as soon as application is started.
     * @param kafkaTemplate injected bean from KafkaProducerConfig
     */
    @Bean
    CommandLineRunner cmdRunner(KafkaTemplate<String, String> kafkaTemplate) {
        // only 1 method 'run' defined in the interface => implemented here
        return args -> {
            for (int i = 0; i < 5; i++) {
                kafkaTemplate.send("kafkaTopic", "New Entries produced" + i);
            }
        };
    }

}
