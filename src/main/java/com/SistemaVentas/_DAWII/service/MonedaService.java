package com.SistemaVentas._DAWII.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.SistemaVentas._DAWII.model.Moneda;

public interface MonedaService {
    
    ResponseEntity<Map<String, Object>> listarMonedas();
    
    ResponseEntity<Map<String, Object>> listarMonedaPorId(Long id);
    
    ResponseEntity<Map<String, Object>> agregarMoneda(Moneda moneda);
    
    ResponseEntity<Map<String, Object>> editarMoneda(Moneda moneda, Long id);
    
    ResponseEntity<Map<String, Object>> eliminarMoneda(Long id);
}
