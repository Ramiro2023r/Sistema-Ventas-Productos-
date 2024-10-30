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

import com.SistemaVentas._DAWII.model.Producto;
import com.SistemaVentas._DAWII.repository.ProductoRepository;
import com.SistemaVentas._DAWII.service.ProductoService;

@Service
public class ProductoServiceImplement implements ProductoService {

    @Autowired
    private ProductoRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarProductos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Producto> productos = dao.findAll();

        if (!productos.isEmpty()) {
            respuesta.put("mensaje", "Lista de productos");
            respuesta.put("productos", productos);
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
    public ResponseEntity<Map<String, Object>> listarProductosPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> producto = dao.findById(id);

        if (producto.isPresent()) {
            respuesta.put("productos", producto.get()); // Cambiado para obtener el objeto Producto
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
    public ResponseEntity<Map<String, Object>> agregarProductos(Producto producto) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(producto);
        respuesta.put("productos", producto);
        respuesta.put("mensaje", "Se añadió correctamente el producto");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarProductos(Producto prd, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> productoExiste = dao.findById(id);

        if (productoExiste.isPresent()) {
            Producto producto = productoExiste.get();
            producto.setDescripcion(prd.getDescripcion());
            producto.setCantidad(prd.getCantidad());
            producto.setPrecioCompra(prd.getPrecioCompra());
            producto.setPrecioVenta(prd.getPrecioVenta());
            producto.setMedida(prd.getMedida()); // Cambiado para utilizar la relación
            producto.setCategoria(prd.getCategoria()); // Cambiado para utilizar la relación
            producto.setFoto(prd.getFoto());
            producto.setEstado(prd.getEstado());
            producto.setFecha(prd.getFecha());
            dao.save(producto);
            respuesta.put("productos", producto);
            respuesta.put("mensaje", "Datos del producto modificados");
            respuesta.put("status", HttpStatus.OK); // Cambiado a OK, ya que la modificación se realiza correctamente
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
    public ResponseEntity<Map<String, Object>> eliminarProductos(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> productoExiste = dao.findById(id);

        if (productoExiste.isPresent()) {
            Producto producto = productoExiste.get();
            dao.delete(producto);
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
