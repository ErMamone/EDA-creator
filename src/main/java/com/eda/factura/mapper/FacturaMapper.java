package com.eda.factura.mapper;

import com.eda.factura.dto.FacturaRequest;
import com.eda.factura.entities.Factura;
import com.eda.factura.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "items", source = "items")
    Factura toEntity(FacturaRequest request, List<Item> items, double total);

}
