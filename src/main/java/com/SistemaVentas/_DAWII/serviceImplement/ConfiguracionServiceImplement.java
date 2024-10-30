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

import com.SistemaVentas._DAWII.model.Configuracion;
import com.SistemaVentas._DAWII.repository.ConfiguracionRepository;
import com.SistemaVentas._DAWII.service.ConfiguracionService;

@Service
public class ConfiguracionServiceImplement implements ConfiguracionService {

    @Autowired
    private ConfiguracionRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarConfiguraciones() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Configuracion> configuraciones = dao.findAll();

        if (!configuraciones.isEmpty()) {
            respuesta.put("mensaje", "Lista de configuraciones");
            respuesta.put("configuraciones", configuraciones);
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
    public ResponseEntity<Map<String, Object>> listarConfiguracionPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Configuracion> configuracion = dao.findById(id);

        if (configuracion.isPresent()) {
            respuesta.put("configuracion", configuracion.get());
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
    public ResponseEntity<Map<String, Object>> agregarConfiguracion(Configuracion configuracion) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(configuracion);
        respuesta.put("configuracion", configuracion);
        respuesta.put("mensaje", "Se añadió correctamente la configuración");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarConfiguracion(Configuracion configuracion, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Configuracion> configuracionExistente = dao.findById(id);
        
        if (configuracionExistente.isPresent()) {
            Configuracion configuracionActual = configuracionExistente.get();
            configuracionActual.setRuc(configuracion.getRuc());
            configuracionActual.setNombre(configuracion.getNombre());
            configuracionActual.setTelefono(configuracion.getTelefono());
            configuracionActual.setCorreo(configuracion.getCorreo());
            configuracionActual.setDireccion(configuracion.getDireccion());
            configuracionActual.setMensaje(configuracion.getMensaje());
            configuracionActual.setLogo(configuracion.getLogo());
            configuracionActual.setMoneda(configuracion.getMoneda());
            configuracionActual.setImpuesto(configuracion.getImpuesto());
            configuracionActual.setCant_factura(configuracion.getCant_factura());
            configuracionActual.setSite(configuracion.getSite());
            dao.save(configuracionActual);
            respuesta.put("configuracion", configuracionActual);
            respuesta.put("mensaje", "Datos de la configuración modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarConfiguracion(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Configuracion> configuracionExistente = dao.findById(id);
        
        if (configuracionExistente.isPresent()) {
            dao.delete(configuracionExistente.get());
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
