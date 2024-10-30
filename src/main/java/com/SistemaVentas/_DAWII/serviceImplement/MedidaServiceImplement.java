package com.SistemaVentas._DAWII.serviceImplement;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SistemaVentas._DAWII.model.Medida;
import com.SistemaVentas._DAWII.repository.MedidaRepository;
import com.SistemaVentas._DAWII.service.MedidaService;

@Service
public class MedidaServiceImplement implements MedidaService {

    @Autowired
    private MedidaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarMedidas() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Medida> medidas = dao.findAll();

        if (!medidas.isEmpty()) {
            respuesta.put("mensaje", "Lista de medidas");
            respuesta.put("medidas", medidas);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarMedidasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Medida> medida = dao.findById(id);

        if (medida.isPresent()) {
            respuesta.put("medida", medida);
            respuesta.put("mensaje", "Búsqueda correcta");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.ok().body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarMedida(Medida medida) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(medida);
        respuesta.put("medida", medida);
        respuesta.put("mensaje", "Se añadió correctamente la medida");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarMedida(Medida medida, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Medida> medidaExistente = dao.findById(id);
        
        if (medidaExistente.isPresent()) {
            Medida medidaActual = medidaExistente.get();
            medidaActual.setNombre(medida.getNombre());
            medidaActual.setNombreCorto(medida.getNombreCorto());
            medidaActual.setEstado(medida.getEstado());
            medidaActual.setFecha(medida.getFecha());
            dao.save(medidaActual);
            respuesta.put("medida", medidaActual);
            respuesta.put("mensaje", "Datos de la medida modificados");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarMedida(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Medida> medidaExistente = dao.findById(id);
        
        if (medidaExistente.isPresent()) {
            dao.delete(medidaExistente.get());
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}