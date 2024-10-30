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

import com.SistemaVentas._DAWII.model.CierreCaja;
import com.SistemaVentas._DAWII.repository.CierreCajaRepository;
import com.SistemaVentas._DAWII.service.CierreCajaService;

@Service
public class CierreCajaServiceImplement implements CierreCajaService {

    @Autowired
    private CierreCajaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCierresCaja() {
        Map<String, Object> respuesta = new HashMap<>();
        List<CierreCaja> cierresCaja = dao.findAll();

        if (!cierresCaja.isEmpty()) {
            respuesta.put("mensaje", "Lista de cierres de caja");
            respuesta.put("cierresCaja", cierresCaja);
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
    public ResponseEntity<Map<String, Object>> listarCierreCajaPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<CierreCaja> cierreCaja = dao.findById(id);

        if (cierreCaja.isPresent()) {
            respuesta.put("cierreCaja", cierreCaja);
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
    public ResponseEntity<Map<String, Object>> agregarCierreCaja(CierreCaja cierreCaja) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(cierreCaja);
        respuesta.put("cierreCaja", cierreCaja);
        respuesta.put("mensaje", "Se añadió correctamente el cierre de caja");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCierreCaja(CierreCaja cierreCaja, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<CierreCaja> cierreCajaExistente = dao.findById(id);
        
        if (cierreCajaExistente.isPresent()) {
            CierreCaja cierreActual = cierreCajaExistente.get();
            cierreActual.setUsuario(cierreCaja.getUsuario());
            cierreActual.setMonto_inicial(cierreCaja.getMonto_inicial());
            cierreActual.setFecha_apertura(cierreCaja.getFecha_apertura());
            cierreActual.setFecha_cierre(cierreCaja.getFecha_cierre());
            cierreActual.setMonto_final(cierreCaja.getMonto_final());
            cierreActual.setTotal_ventas(cierreCaja.getTotal_ventas());
            cierreActual.setMonto_total(cierreCaja.getMonto_total());
            cierreActual.setEstado(cierreCaja.getEstado());
            dao.save(cierreActual);
            respuesta.put("cierreCaja", cierreActual);
            respuesta.put("mensaje", "Datos del cierre de caja modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCierreCaja(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<CierreCaja> cierreCajaExistente = dao.findById(id);
        
        if (cierreCajaExistente.isPresent()) {
            dao.delete(cierreCajaExistente.get());
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
