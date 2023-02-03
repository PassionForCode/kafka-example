package com.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    /**
     * This listener always reads from his last offset, i.e. when the application is started, it only outputs
     * the messages that are created on startup - and further messages that are created by other producers
     *
     * @param data the received data
     */
    @KafkaListener(topics = "kafkaTopic", groupId = "newGroupId")
    void listener(String data) {
        System.out.println("Listener received " + data + "❤️");
    }

    /**
     * This listener always reads from the beginning of the partition, i.e. outputs ALL messages
     * @param message the received message
     * @param partition the partition where the message is received from
     */
    @KafkaListener(groupId = "readfromBeginningGroup",
            topicPartitions = @TopicPartition(topic = "kafkaTopic",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0")}))
    public void listenToPartition(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println(
                "Received Message: " + message + " from partition: " + partition + " 👌");
    }

}
