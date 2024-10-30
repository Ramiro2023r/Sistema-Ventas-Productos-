package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Venta;



public interface VentaService {

    public ResponseEntity<Map<String, Object>> listarVentas();

    public ResponseEntity<Map<String, Object>> listarVentasPorId(Long id);

    public ResponseEntity<Map<String, Object>> agregarVenta(Venta  venta);

    public ResponseEntity<Map<String, Object>> editarVenta(Venta venta, Long id);

    public ResponseEntity<Map<String, Object>> eliminarVenta(Long id);
}
