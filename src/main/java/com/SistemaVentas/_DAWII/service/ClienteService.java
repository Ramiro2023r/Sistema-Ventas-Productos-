package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Cliente;

public interface ClienteService {
    
    public ResponseEntity<Map<String, Object>> listarClientes();
    
    public ResponseEntity<Map<String, Object>> listarClientesPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarCliente(Cliente cliente);
    
    public ResponseEntity<Map<String, Object>> editarCliente(Cliente cliente, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarCliente(Long id);
}
