package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Credito;

public interface CreditoService {
    
    ResponseEntity<Map<String, Object>> listarCreditos();
    
    ResponseEntity<Map<String, Object>> listarCreditoPorId(Long id);
    
    ResponseEntity<Map<String, Object>> agregarCredito(Credito credito);
    
    ResponseEntity<Map<String, Object>> editarCredito(Credito credito, Long id);
    
    ResponseEntity<Map<String, Object>> eliminarCredito(Long id);
}
