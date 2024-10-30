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

import com.SistemaVentas._DAWII.model.Proveedor;
import com.SistemaVentas._DAWII.repository.ProveedorRepository;
import com.SistemaVentas._DAWII.service.ProveedorService;

@Service
public class ProveedorServiceImplement implements ProveedorService {

    @Autowired
    private ProveedorRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarProveedores() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Proveedor> proveedores = dao.findAll();

        if (!proveedores.isEmpty()) {
            respuesta.put("mensaje", "Lista de proveedores");
            respuesta.put("proveedores", proveedores);
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
    public ResponseEntity<Map<String, Object>> listarProveedoresPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedor = dao.findById(id);

        if (proveedor.isPresent()) {
            respuesta.put("proveedor", proveedor);
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
    public ResponseEntity<Map<String, Object>> agregarProveedor(Proveedor proveedor) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(proveedor);
        respuesta.put("proveedor", proveedor);
        respuesta.put("mensaje", "Se añadió correctamente el proveedor");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarProveedor(Proveedor proveedor, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedorExistente = dao.findById(id);

        if (proveedorExistente.isPresent()) {
            Proveedor proveedorActual = proveedorExistente.get();
            proveedorActual.setRuc(proveedor.getRuc());
            proveedorActual.setNombre(proveedor.getNombre());
            proveedorActual.setTelefono(proveedor.getTelefono());
            proveedorActual.setDireccion(proveedor.getDireccion());
            proveedorActual.setEstado(proveedor.getEstado());
            dao.save(proveedorActual);
            respuesta.put("proveedor", proveedorActual);
            respuesta.put("mensaje", "Datos del proveedor modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarProveedor(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedorExistente = dao.findById(id);

        if (proveedorExistente.isPresent()) {
            dao.delete(proveedorExistente.get());
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
