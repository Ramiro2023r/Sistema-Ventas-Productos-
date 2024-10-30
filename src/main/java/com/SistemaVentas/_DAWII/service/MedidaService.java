package com.SistemaVentas._DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SistemaVentas._DAWII.model.Medida;

public interface MedidaService {
	
	 public ResponseEntity<Map<String, Object>> listarMedidas();
	    
	    public ResponseEntity<Map<String, Object>> listarMedidasPorId(Long id);
	    
	    public ResponseEntity<Map<String, Object>> agregarMedida(Medida medida);
	    
	    public ResponseEntity<Map<String, Object>> editarMedida(Medida medida, Long id);
	    
	    public ResponseEntity<Map<String, Object>> eliminarMedida(Long id);


}
