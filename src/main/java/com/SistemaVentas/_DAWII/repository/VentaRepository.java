package com.SistemaVentas._DAWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaVentas._DAWII.model.Venta;

@Repository
public interface VentaRepository   extends JpaRepository<Venta, Long>  {

}
