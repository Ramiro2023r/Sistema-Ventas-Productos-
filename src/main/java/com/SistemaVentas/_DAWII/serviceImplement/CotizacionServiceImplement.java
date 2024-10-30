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

import com.SistemaVentas._DAWII.model.Cotizacion;
import com.SistemaVentas._DAWII.repository.CotizacionRepository;
import com.SistemaVentas._DAWII.service.CotizacionService;

@Service
public class CotizacionServiceImplement implements CotizacionService {

    @Autowired
    private CotizacionRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCotizaciones() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Cotizacion> cotizaciones = dao.findAll();

        if (!cotizaciones.isEmpty()) {
            respuesta.put("mensaje", "Lista de cotizaciones");
            respuesta.put("cotizaciones", cotizaciones);
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
    public ResponseEntity<Map<String, Object>> listarCotizacionPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cotizacion> cotizacion = dao.findById(id);

        if (cotizacion.isPresent()) {
            respuesta.put("cotizacion", cotizacion.get());
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
    public ResponseEntity<Map<String, Object>> agregarCotizacion(Cotizacion cotizacion) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(cotizacion);
        respuesta.put("cotizacion", cotizacion);
        respuesta.put("mensaje", "Se añadió correctamente la cotización");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCotizacion(Cotizacion cotizacion, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cotizacion> cotizacionExistente = dao.findById(id);
        
        if (cotizacionExistente.isPresent()) {
            Cotizacion cotizacionActual = cotizacionExistente.get();
            cotizacionActual.setProductos(cotizacion.getProductos());
            cotizacionActual.setTotal(cotizacion.getTotal());
            cotizacionActual.setFecha(cotizacion.getFecha());
            cotizacionActual.setHora(cotizacion.getHora());
            cotizacionActual.setValidez(cotizacion.getValidez());
            cotizacionActual.setComentario(cotizacion.getComentario());
            cotizacionActual.setCliente(cotizacion.getCliente());
            cotizacionActual.setUsuario(cotizacion.getUsuario());
            dao.save(cotizacionActual);
            respuesta.put("cotizacion", cotizacionActual);
            respuesta.put("mensaje", "Datos de la cotización modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCotizacion(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cotizacion> cotizacionExistente = dao.findById(id);
        
        if (cotizacionExistente.isPresent()) {
            dao.delete(cotizacionExistente.get());
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
