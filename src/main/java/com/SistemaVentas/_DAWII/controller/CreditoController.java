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

import com.SistemaVentas._DAWII.model.Credito; // Asegúrate de importar la clase Creditos
import com.SistemaVentas._DAWII.service.CreditoService; // Asegúrate de importar el servicio CreditoService

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarCreditos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id) {
        return service.listarCreditoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Credito credito) {
        return service.agregarCredito(credito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Credito credito, @PathVariable Long id) {
        return service.editarCredito(credito, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarCredito(id);
    }
}
