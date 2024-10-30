package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Cotizacion;

public interface CotizacionService {
    
    ResponseEntity<Map<String, Object>> listarCotizaciones();
    
    ResponseEntity<Map<String, Object>> listarCotizacionPorId(Long id);
    
    ResponseEntity<Map<String, Object>> agregarCotizacion(Cotizacion cotizacion);
    
    ResponseEntity<Map<String, Object>> editarCotizacion(Cotizacion cotizacion, Long id);
    
    ResponseEntity<Map<String, Object>> eliminarCotizacion(Long id);
}
