package com.SistemaVentas._DAWII.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaVentas._DAWII.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findOneByEmail(String email);

}
