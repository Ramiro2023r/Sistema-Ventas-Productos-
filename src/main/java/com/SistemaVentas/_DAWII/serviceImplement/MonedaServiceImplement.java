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

import com.SistemaVentas._DAWII.model.Moneda;
import com.SistemaVentas._DAWII.repository.MonedaRepository;
import com.SistemaVentas._DAWII.service.MonedaService;

@Service
public class MonedaServiceImplement implements MonedaService {

    @Autowired
    private MonedaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarMonedas() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Moneda> monedas = dao.findAll();

        if (!monedas.isEmpty()) {
            respuesta.put("mensaje", "Lista de monedas");
            respuesta.put("monedas", monedas);
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
    public ResponseEntity<Map<String, Object>> listarMonedaPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Moneda> moneda = dao.findById(id);

        if (moneda.isPresent()) {
            respuesta.put("moneda", moneda);
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
    public ResponseEntity<Map<String, Object>> agregarMoneda(Moneda moneda) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(moneda);
        respuesta.put("moneda", moneda);
        respuesta.put("mensaje", "Se añadió correctamente la moneda");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarMoneda(Moneda moneda, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Moneda> monedaExistente = dao.findById(id);
        
        if (monedaExistente.isPresent()) {
            Moneda monedaActual = monedaExistente.get();
            monedaActual.setNombre(moneda.getNombre());
            monedaActual.setSimbolo(moneda.getSimbolo());
            monedaActual.setEstado(moneda.getEstado());
            monedaActual.setFecha(moneda.getFecha());
            dao.save(monedaActual);
            respuesta.put("moneda", monedaActual);
            respuesta.put("mensaje", "Datos de la moneda modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarMoneda(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Moneda> monedaExistente = dao.findById(id);
        
        if (monedaExistente.isPresent()) {
            dao.delete(monedaExistente.get());
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
