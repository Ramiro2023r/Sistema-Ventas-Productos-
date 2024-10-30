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

import com.SistemaVentas._DAWII.model.Categoria;
import com.SistemaVentas._DAWII.repository.CategoriaRepository;
import com.SistemaVentas._DAWII.service.CategoriaService;

@Service
public class CategoriaServiceImplement implements CategoriaService {

    @Autowired
    private CategoriaRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarCategorias() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Categoria> categorias = dao.findAll();

        if (!categorias.isEmpty()) {
            respuesta.put("mensaje", "Lista de categorías");
            respuesta.put("categorias", categorias);
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
    public ResponseEntity<Map<String, Object>> listarCategoriasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Categoria> categoria = dao.findById(id);

        if (categoria.isPresent()) {
            respuesta.put("categoria", categoria);
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
    public ResponseEntity<Map<String, Object>> agregarCategoria(Categoria categoria) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(categoria);
        respuesta.put("categoria", categoria);
        respuesta.put("mensaje", "Se añadió correctamente la categoría");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCategoria(Categoria categoria, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Categoria> categoriaExistente = dao.findById(id);
        
        if (categoriaExistente.isPresent()) {
            Categoria categoriaActual = categoriaExistente.get();
            categoriaActual.setNombre(categoria.getNombre());
            categoriaActual.setEstado(categoria.getEstado());
            categoriaActual.setFecha(categoria.getFecha());
            dao.save(categoriaActual);
            respuesta.put("categoria", categoriaActual);
            respuesta.put("mensaje", "Datos de la categoría modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCategoria(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Categoria> categoriaExistente = dao.findById(id);
        
        if (categoriaExistente.isPresent()) {
            dao.delete(categoriaExistente.get());
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