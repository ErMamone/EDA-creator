package com.eda.factura.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KafkaTopic {
    FACTURAS_CREADAS("kafka.topic.facturas-creadas");

    @Getter
    private final String propertyKey;
}
