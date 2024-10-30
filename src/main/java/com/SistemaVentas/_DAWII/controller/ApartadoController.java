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

import com.SistemaVentas._DAWII.model.Apartado;
import com.SistemaVentas._DAWII.service.ApartadoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/apartados")
public class ApartadoController {

    @Autowired
    private ApartadoService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarApartados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id) {
        return service.listarApartadoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Apartado apartado) {
        return service.agregarApartado(apartado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Apartado apartado, @PathVariable Long id) {
        return service.editarApartado(apartado, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarApartado(id);
    }
}
