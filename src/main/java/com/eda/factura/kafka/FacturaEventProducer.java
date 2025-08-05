package com.eda.factura.kafka;

import com.eda.factura.config.KafkaTopicResolver;
import com.eda.factura.utils.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FacturaEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicResolver topicResolver;

    public void createFactura(KafkaTopic topic, String mensajeJson) {
        String topicName = topicResolver.resolve(topic);

        var message = MessageBuilder.
                withPayload(mensajeJson)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.KEY, "key")
                .build();

        kafkaTemplate.send(message);
    }
}
