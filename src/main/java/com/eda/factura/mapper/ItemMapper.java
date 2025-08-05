package com.eda.factura.mapper;

import com.eda.factura.dto.FacturaRequest;
import com.eda.factura.dto.ItemDTO;
import com.eda.factura.entities.Factura;
import com.eda.factura.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemDTO items);

}
