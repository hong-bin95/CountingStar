package com.ssafy.countingstar.component;

import java.util.HashMap;
import java.util.Map;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Component
public class KafkaBuilder {

    private final String bootstrapServers;

    public KafkaBuilder(@Value("${spring.kafka.producer.bootstrap-servers}") String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }
    
    public <R> KafkaSender<String, R> kafkaSender(String canonicalSerializerClassPath) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, canonicalSerializerClassPath);
        SenderOptions<String, R> senderOptions = SenderOptions.create(configProps);
        senderOptions = senderOptions.scheduler(Schedulers.boundedElastic()).maxInFlight(4096); ;
        return KafkaSender.create(senderOptions);
    }
    
    public <T> KafkaReceiver<String, T> kafkaReceiver(String canonicalSerializerClassPath) {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, canonicalSerializerClassPath);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "countingstar");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ReceiverOptions<String, T> receiverOptions = ReceiverOptions.<String, T>create(consumerProps);
        return KafkaReceiver.create(receiverOptions);
    }
}