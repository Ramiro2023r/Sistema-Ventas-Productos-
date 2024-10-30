package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Categoria;

public interface CategoriaService {

	
	 public ResponseEntity<Map<String, Object>> listarCategorias();
	    
	    public ResponseEntity<Map<String, Object>> listarCategoriasPorId(Long id);
	    
	    public ResponseEntity<Map<String, Object>> agregarCategoria(Categoria categoria);
	    
	    public ResponseEntity<Map<String, Object>> editarCategoria(Categoria categoria, Long id);
	    
	    public ResponseEntity<Map<String, Object>> eliminarCategoria(Long id);
}
