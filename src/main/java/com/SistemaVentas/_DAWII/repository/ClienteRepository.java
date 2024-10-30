package com.SistemaVentas._DAWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaVentas._DAWII.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
