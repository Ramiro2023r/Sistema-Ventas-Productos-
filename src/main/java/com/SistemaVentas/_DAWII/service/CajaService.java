package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Caja;

public interface CajaService {

    public ResponseEntity<Map<String, Object>> listarCajas();
    
    public ResponseEntity<Map<String, Object>> listarCajasPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarCaja(Caja caja);
    
    public ResponseEntity<Map<String, Object>> editarCaja(Caja caja, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarCaja(Long id);
}
