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

import com.SistemaVentas._DAWII.model.CierreCaja;
import com.SistemaVentas._DAWII.service.CierreCajaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cierres-caja")
public class CierreCajaController {

    @Autowired
    private CierreCajaService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarCierresCaja();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorID(@PathVariable Long id) {
        return service.listarCierreCajaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody CierreCaja cierreCaja) {
        return service.agregarCierreCaja(cierreCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody CierreCaja cierreCaja, @PathVariable Long id) {
        return service.editarCierreCaja(cierreCaja, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarCierreCaja(id);
    }
}
