package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.CierreCaja;

public interface CierreCajaService {

    public ResponseEntity<Map<String, Object>> listarCierresCaja();
    
    public ResponseEntity<Map<String, Object>> listarCierreCajaPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarCierreCaja(CierreCaja cierreCaja);
    
    public ResponseEntity<Map<String, Object>> editarCierreCaja(CierreCaja cierreCaja, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarCierreCaja(Long id);
}
