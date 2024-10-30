package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Apartado;

public interface ApartadoService {

    public ResponseEntity<Map<String, Object>> listarApartados();
    
    public ResponseEntity<Map<String, Object>> listarApartadoPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarApartado(Apartado apartado);
    
    public ResponseEntity<Map<String, Object>> editarApartado(Apartado apartado, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarApartado(Long id);
}
