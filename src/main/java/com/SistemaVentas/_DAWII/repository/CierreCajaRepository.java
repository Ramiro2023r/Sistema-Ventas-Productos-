package com.SistemaVentas._DAWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaVentas._DAWII.model.CierreCaja;

@Repository
public interface CierreCajaRepository extends JpaRepository<CierreCaja, Long>{

}
