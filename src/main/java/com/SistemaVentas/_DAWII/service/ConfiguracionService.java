package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Configuracion;

public interface ConfiguracionService {
    
    public ResponseEntity<Map<String, Object>> listarConfiguraciones();
    
    public ResponseEntity<Map<String, Object>> listarConfiguracionPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarConfiguracion(Configuracion configuracion);
    
    public ResponseEntity<Map<String, Object>> editarConfiguracion(Configuracion configuracion, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarConfiguracion(Long id);
}
