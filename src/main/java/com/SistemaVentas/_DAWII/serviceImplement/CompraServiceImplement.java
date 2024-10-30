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

import com.SistemaVentas._DAWII.model.Compra;
import com.SistemaVentas._DAWII.repository.CompraRepository;
import com.SistemaVentas._DAWII.service.CompraService;

@Service
public class CompraServiceImplement implements CompraService {

    @Autowired
    private CompraRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCompras() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Compra> compras = dao.findAll();

        if (!compras.isEmpty()) {
            respuesta.put("mensaje", "Lista de compras");
            respuesta.put("compras", compras);
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
    public ResponseEntity<Map<String, Object>> listarComprasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Compra> compra = dao.findById(id);

        if (compra.isPresent()) {
            respuesta.put("compra", compra.get());
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
    public ResponseEntity<Map<String, Object>> agregarCompra(Compra compra) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(compra);
        respuesta.put("compra", compra);
        respuesta.put("mensaje", "Se añadió correctamente la compra");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCompra(Compra compra, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Compra> compraExistente = dao.findById(id);
        
        if (compraExistente.isPresent()) {
            Compra compraActual = compraExistente.get();
            compraActual.setProveedor(compra.getProveedor());
            compraActual.setUsuario(compra.getUsuario());
            compraActual.setTotal(compra.getTotal());
            compraActual.setFecha(compra.getFecha());
            compraActual.setHora(compra.getHora());
            compraActual.setSerie(compra.getSerie());
            compraActual.setEstado(compra.getEstado());
            compraActual.setMetodo(compra.getMetodo());
            dao.save(compraActual);
            respuesta.put("compra", compraActual);
            respuesta.put("mensaje", "Datos de la compra modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCompra(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Compra> compraExistente = dao.findById(id);
        
        if (compraExistente.isPresent()) {
            dao.delete(compraExistente.get());
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
