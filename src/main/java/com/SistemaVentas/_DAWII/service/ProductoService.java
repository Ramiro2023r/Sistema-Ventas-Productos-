package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Producto;


public interface ProductoService {
	
public ResponseEntity<Map<String, Object>> listarProductos();
	
	public ResponseEntity<Map<String, Object>> listarProductosPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregarProductos(Producto producto);
	
	public ResponseEntity<Map<String, Object>> editarProductos(Producto producto, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarProductos(Long id);

}
