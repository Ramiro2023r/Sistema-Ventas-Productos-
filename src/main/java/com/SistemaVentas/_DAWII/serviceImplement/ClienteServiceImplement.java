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

import com.SistemaVentas._DAWII.model.Cliente;
import com.SistemaVentas._DAWII.repository.ClienteRepository;
import com.SistemaVentas._DAWII.service.ClienteService;

@Service
public class ClienteServiceImplement implements ClienteService {

    @Autowired
    private ClienteRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarClientes() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Cliente> clientes = dao.findAll();

        if (!clientes.isEmpty()) {
            respuesta.put("mensaje", "Lista de clientes");
            respuesta.put("clientes", clientes);
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
    public ResponseEntity<Map<String, Object>> listarClientesPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> cliente = dao.findById(id);

        if (cliente.isPresent()) {
            respuesta.put("cliente", cliente.get());
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
    public ResponseEntity<Map<String, Object>> agregarCliente(Cliente cliente) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(cliente);
        respuesta.put("cliente", cliente);
        respuesta.put("mensaje", "Se añadió correctamente el cliente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCliente(Cliente cliente, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> clienteExistente = dao.findById(id);
        
        if (clienteExistente.isPresent()) {
            Cliente clienteActual = clienteExistente.get();
            clienteActual.setDni(cliente.getDni());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setTelefono(cliente.getTelefono());
            clienteActual.setDireccion(cliente.getDireccion());
            clienteActual.setEstado(cliente.getEstado());
            dao.save(clienteActual);
            respuesta.put("cliente", clienteActual);
            respuesta.put("mensaje", "Datos del cliente modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarCliente(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> clienteExistente = dao.findById(id);
        
        if (clienteExistente.isPresent()) {
            dao.delete(clienteExistente.get());
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
