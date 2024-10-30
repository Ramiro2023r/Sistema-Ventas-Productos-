package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Proveedor;

public interface ProveedorService {
    
    public ResponseEntity<Map<String, Object>> listarProveedores();
    
    public ResponseEntity<Map<String, Object>> listarProveedoresPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarProveedor(Proveedor proveedor);
    
    public ResponseEntity<Map<String, Object>> editarProveedor(Proveedor proveedor, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarProveedor(Long id);
}
