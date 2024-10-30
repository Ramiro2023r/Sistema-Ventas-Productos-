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

import com.SistemaVentas._DAWII.model.Credito; // Asegúrate de importar la clase Creditos
import com.SistemaVentas._DAWII.repository.CreditoRepository; // Asegúrate de importar el repositorio de Creditos
import com.SistemaVentas._DAWII.service.CreditoService;

@Service
public class CreditoServiceImplement implements CreditoService {

    @Autowired
    private CreditoRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCreditos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Credito> creditos = dao.findAll();

        if (!creditos.isEmpty()) {
            respuesta.put("mensaje", "Lista de créditos");
            respuesta.put("creditos", creditos);
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
    public ResponseEntity<Map<String, Object>> listarCreditoPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Credito> credito = dao.findById(id);

        if (credito.isPresent()) {
            respuesta.put("credito", credito.get());
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
    public ResponseEntity<Map<String, Object>> agregarCredito(Credito credito) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(credito);
        respuesta.put("credito", credito);
        respuesta.put("mensaje", "Se añadió correctamente el crédito");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCredito(Credito credito, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Credito> creditoExistente = dao.findById(id);
        
        if (creditoExistente.isPresent()) {
            Credito creditoActual = creditoExistente.get();
            creditoActual.setMonto(credito.getMonto());
            creditoActual.setEstado(credito.getEstado());
            creditoActual.setVenta(credito.getVenta());
            dao.save(creditoActual);
            respuesta.put("credito", creditoActual);
            respuesta.put("mensaje", "Datos del crédito modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCredito(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Credito> creditoExistente = dao.findById(id);
        
        if (creditoExistente.isPresent()) {
            dao.delete(creditoExistente.get());
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
