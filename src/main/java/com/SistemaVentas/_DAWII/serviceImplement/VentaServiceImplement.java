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

import com.SistemaVentas._DAWII.model.Venta;

import com.SistemaVentas._DAWII.repository.VentaRepository; // Asegúrate de tener este repositorio
import com.SistemaVentas._DAWII.service.VentaService;

@Service
public class VentaServiceImplement implements VentaService {

    @Autowired
    private VentaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarVentas() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Venta> ventas = dao.findAll();

        if (!ventas.isEmpty()) {
            respuesta.put("mensaje", "Lista de ventas");
            respuesta.put("ventas", ventas);
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
    public ResponseEntity<Map<String, Object>> listarVentasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Venta> venta = dao.findById(id);

        if (venta.isPresent()) {
            respuesta.put("venta", venta);
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
    public ResponseEntity<Map<String, Object>> agregarVenta(Venta venta) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(venta);
        respuesta.put("venta", venta);
        respuesta.put("mensaje", "Se añadió correctamente la venta");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarVenta(Venta venta, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Venta> ventaExistente = dao.findById(id);

        if (ventaExistente.isPresent()) {
            Venta ventaActual = ventaExistente.get();
            // Actualiza los campos de la venta según lo que necesites
            ventaActual.setUsuario(venta.getUsuario());
            ventaActual.setCliente(venta.getCliente());
            ventaActual.setTotal(venta.getTotal());
            ventaActual.setFecha(venta.getFecha());
            ventaActual.setHora(venta.getHora());
            ventaActual.setEstado(venta.getEstado());
            ventaActual.setApertura(venta.getApertura());
            ventaActual.setSerie(venta.getSerie());
            ventaActual.setMetodo(venta.getMetodo());
            dao.save(ventaActual);
            respuesta.put("venta", ventaActual);
            respuesta.put("mensaje", "Datos de la venta modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarVenta(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Venta> ventaExistente = dao.findById(id);

        if (ventaExistente.isPresent()) {
            dao.delete(ventaExistente.get());
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
