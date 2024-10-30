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

import com.SistemaVentas._DAWII.model.Caja;
import com.SistemaVentas._DAWII.repository.CajaRepository;
import com.SistemaVentas._DAWII.service.CajaService;

@Service
public class CajaServiceImplement implements CajaService {

    @Autowired
    private CajaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCajas() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Caja> cajas = dao.findAll();

        if (!cajas.isEmpty()) {
            respuesta.put("mensaje", "Lista de cajas");
            respuesta.put("cajas", cajas);
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
    public ResponseEntity<Map<String, Object>> listarCajasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Caja> caja = dao.findById(id);

        if (caja.isPresent()) {
            respuesta.put("caja", caja);
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
    public ResponseEntity<Map<String, Object>> agregarCaja(Caja caja) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(caja);
        respuesta.put("caja", caja);
        respuesta.put("mensaje", "Se añadió correctamente la caja");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCaja(Caja caja, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Caja> cajaExistente = dao.findById(id);
        
        if (cajaExistente.isPresent()) {
            Caja cajaActual = cajaExistente.get();
            cajaActual.setCaja(caja.getCaja());
            cajaActual.setEstado(caja.getEstado());
            cajaActual.setFecha(caja.getFecha());
            dao.save(cajaActual);
            respuesta.put("caja", cajaActual);
            respuesta.put("mensaje", "Datos de la caja modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCaja(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Caja> cajaExistente = dao.findById(id);
        
        if (cajaExistente.isPresent()) {
            dao.delete(cajaExistente.get());
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
