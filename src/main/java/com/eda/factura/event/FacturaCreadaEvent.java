package com.eda.factura.event;

import com.eda.factura.utils.EventType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaCreadaEvent implements Serializable {

    private Long facturaId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime publishDateTime;

    private String clienteId;

    private String vendedorId;

    private List<ItemPayload> items;

    private double total;
}