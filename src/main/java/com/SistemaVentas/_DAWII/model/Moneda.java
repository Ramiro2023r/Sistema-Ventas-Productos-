package com.SistemaVentas._DAWII.model;

import lombok.Data;
import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "moneda")
@EntityListeners(AuditingEntityListener.class)
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String simbolo;

  
    private String nombre;

 
    private Timestamp fecha;

  
    private Integer estado = 1; 
}
