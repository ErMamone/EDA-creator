package com.eda.factura.services;

import com.eda.factura.dto.FacturaRequest;
import com.eda.factura.entities.Factura;
import com.eda.factura.entities.Item;
import com.eda.factura.event.FacturaCreadaEvent;
import com.eda.factura.event.ItemPayload;
import com.eda.factura.kafka.FacturaEventProducer;
import com.eda.factura.mapper.FacturaMapper;
import com.eda.factura.mapper.ItemMapper;
import com.eda.factura.repositories.FacturaRepository;
import com.eda.factura.utils.EventType;
import com.eda.factura.utils.KafkaTopic;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper mapper;
    private final ItemMapper itemMapper;
    private final FacturaEventProducer producer;
    private final ObjectMapper om;

    public void procesarFactura(FacturaRequest request) throws Exception {
        double total = getTotal(request);

        List<Item> items = request.getItems().stream().map(itemMapper::toEntity).toList();

        var evento = saveAndCreateFacturaEvent(request, items, total);

        producer.createFactura(KafkaTopic.FACTURAS_CREADAS, om.writeValueAsString(evento));
    }

    private FacturaCreadaEvent saveAndCreateFacturaEvent(FacturaRequest request, List<Item> items, double total) {
        Factura factura = mapper.toEntity(request,
                items,
                total);

        factura = facturaRepository.save(factura);

        List<ItemPayload> payloadItems = items.stream()
                .map(i -> ItemPayload.builder()
                        .producto(i.getProducto())
                        .cantidad(i.getCantidad())
                        .precio(i.getPrecio())
                        .build())
                .toList();

        return FacturaCreadaEvent.builder()
                .eventType(EventType.FACTURA_CREADA)
                .publishDateTime(LocalDateTime.now())
                .facturaId(factura.getId())
                .clienteId(factura.getClienteId())
                .vendedorId(factura.getVendedorId())
                .items(payloadItems)
                .total(factura.getTotal())
                .build();
    }

    private static double getTotal(FacturaRequest request) {
        return request.getItems().stream()
                .mapToDouble(i -> i.getCantidad() * i.getPrecio())
                .sum();
    }
}