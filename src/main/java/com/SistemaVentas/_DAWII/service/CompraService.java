package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Compra;

public interface CompraService {
    
    public ResponseEntity<Map<String, Object>> listarCompras();
    
    public ResponseEntity<Map<String, Object>> listarComprasPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarCompra(Compra compra);
    
    public ResponseEntity<Map<String, Object>> editarCompra(Compra compra, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarCompra(Long id);
}
