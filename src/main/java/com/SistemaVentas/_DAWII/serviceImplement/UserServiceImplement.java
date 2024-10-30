package com.SistemaVentas._DAWII.serviceImplement;






import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SistemaVentas._DAWII.model.Usuario;
import com.SistemaVentas._DAWII.repository.UsuarioRepository;




@Service
public class UserServiceImplement implements UserDetailsService{

	@Autowired
	private UsuarioRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = dao.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: "+email+ "no existe."));
		
		return new UserDetailImplement(usuario);
	}
		
}

























/*
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SistemaVentas._DAWII.model.Usuario;
import com.SistemaVentas._DAWII.repository.UsuarioRepository;
import com.SistemaVentas._DAWII.service.UsuarioService;

@Service
public class UsuarioServiceImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Usuario> usuarios = dao.findAll();

        if (!usuarios.isEmpty()) {
            respuesta.put("mensaje", "Lista de usuarios");
            respuesta.put("usuarios", usuarios);
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
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuario = dao.findById(id);

        if (usuario.isPresent()) {
            respuesta.put("usuario", usuario);
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
    public ResponseEntity<Map<String, Object>> agregarUsuario(Usuario usuario) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(usuario);
        respuesta.put("usuario", usuario);
        respuesta.put("mensaje", "Se añadió correctamente el usuario");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarUsuario(Usuario usuario, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuarioExistente = dao.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuarioActual = usuarioExistente.get();
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setEmail(usuario.getEmail());
            usuarioActual.setTelefono(usuario.getTelefono());
            usuarioActual.setDireccion(usuario.getDireccion());
            usuarioActual.setPerfil(usuario.getPerfil());
            usuarioActual.setPassword(usuario.getPassword());
            usuarioActual.setIdCaja(usuario.getIdCaja());
            usuarioActual.setToken(usuario.getToken());
            usuarioActual.setEstado(usuario.getEstado());
            dao.save(usuarioActual);
            respuesta.put("usuario", usuarioActual);
            respuesta.put("mensaje", "Datos del usuario modificados");
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
    public ResponseEntity<Map<String, Object>> eliminarUsuario(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuarioExistente = dao.findById(id);

        if (usuarioExistente.isPresent()) {
            dao.delete(usuarioExistente.get());
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
}*/
