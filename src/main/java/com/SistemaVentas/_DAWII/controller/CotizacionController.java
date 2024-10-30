package com.SistemaVentas._DAWII.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaVentas._DAWII.model.Cotizacion;
import com.SistemaVentas._DAWII.service.CotizacionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarCotizaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id) {
        return service.listarCotizacionPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Cotizacion cotizacion) {
        return service.agregarCotizacion(cotizacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Cotizacion cotizacion, @PathVariable Long id) {
        return service.editarCotizacion(cotizacion, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarCotizacion(id);
    }
}
