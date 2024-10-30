package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Usuario;

public interface UsuarioService {
    
    public ResponseEntity<Map<String, Object>> listarUsuarios();
    
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id);
    
    public ResponseEntity<Map<String, Object>> agregarUsuario(Usuario usuario);
    
    public ResponseEntity<Map<String, Object>> editarUsuario(Usuario usuario, Long id);
    
    public ResponseEntity<Map<String, Object>> eliminarUsuario(Long id);
}
