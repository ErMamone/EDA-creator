package com.eda.factura.controllers;

import com.eda.factura.dto.FacturaRequest;
import com.eda.factura.services.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    public ResponseEntity<?> crearFactura(@RequestBody FacturaRequest request) throws Exception {
        facturaService.procesarFactura(request);
        return ResponseEntity.ok("Factura enviada como evento");
    }
}