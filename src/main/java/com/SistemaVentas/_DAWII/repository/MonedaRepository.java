package com.SistemaVentas._DAWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaVentas._DAWII.model.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Long> {

}
