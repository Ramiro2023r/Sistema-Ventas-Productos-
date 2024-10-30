package com.SistemaVentas._DAWII.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "apartados")
@EntityListeners(AuditingEntityListener.class)
public class Apartado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Mapea el campo `id` de la tabla

    private Timestamp fechaApartado;  // Mapea el campo `fecha_apartado`
    private LocalDateTime fechaRetiro; // Mapea el campo `fecha_retiro`
    
    private BigDecimal abono;  // Mapea el campo `abono` (decimal)
    private BigDecimal total;  // Mapea el campo `total` (decimal)
    
    private String color;      // Mapea el campo `color`
    private int estado;        // Mapea el campo `estado`
    
    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "idCliente") // Columna en la tabla de productos
    private Cliente cliente;

    
}
