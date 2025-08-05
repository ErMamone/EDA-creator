package com.eda.factura.config;

import com.eda.factura.utils.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaTopicResolver {

    private final Environment environment;

    public String resolve(KafkaTopic topicEnum) {
        return environment.getProperty(topicEnum.getPropertyKey());
    }
}
