package com.SistemaVentas._DAWII.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "caja")
@EntityListeners(AuditingEntityListener.class)
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caja; // Este campo mapea a `caja` de la tabla
    private Timestamp fecha;
    private int estado;

    // Puedes agregar constructores y otros métodos si es necesario
}
