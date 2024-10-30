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

import com.SistemaVentas._DAWII.model.Apartado;
import com.SistemaVentas._DAWII.repository.ApartadoRepository;
import com.SistemaVentas._DAWII.service.ApartadoService;

@Service
public class ApartadoServiceImplement implements ApartadoService {

    @Autowired
    private ApartadoRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarApartados() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Apartado> apartados = dao.findAll();

        if (!apartados.isEmpty()) {
            respuesta.put("mensaje", "Lista de apartados");
            respuesta.put("apartados", apartados);
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
    public ResponseEntity<Map<String, Object>> listarApartadoPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Apartado> apartado = dao.findById(id);

        if (apartado.isPresent()) {
            respuesta.put("apartado", apartado);
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
    public ResponseEntity<Map<String, Object>> agregarApartado(Apartado apartado) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(apartado);
        respuesta.put("apartado", apartado);
        respuesta.put("mensaje", "Se añadió correctamente el apartado");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarApartado(Apartado apartado, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Apartado> apartadoExistente = dao.findById(id);
        
        if (apartadoExistente.isPresent()) {
            Apartado apartadoActual = apartadoExistente.get();
            apartadoActual.setFechaApartado(apartado.getFechaApartado());
            apartadoActual.setFechaRetiro(apartado.getFechaRetiro());
            apartadoActual.setAbono(apartado.getAbono());
            apartadoActual.setTotal(apartado.getTotal());
            apartadoActual.setColor(apartado.getColor());
            apartadoActual.setEstado(apartado.getEstado());
            apartadoActual.setCliente(apartado.getCliente());
            dao.save(apartadoActual);
            respuesta.put("apartado", apartadoActual);
            respuesta.put("mensaje", "Datos del apartado modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarApartado(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Apartado> apartadoExistente = dao.findById(id);
        
        if (apartadoExistente.isPresent()) {
            dao.delete(apartadoExistente.get());
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
