package com.SistemaVentas._DAWII.model;

import lombok.Data;

import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Table(name = "ventas")
@EntityListeners(AuditingEntityListener.class)
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne // Relación con Categoria
    @JoinColumn(name = "id_usuario") // Columna en la tabla de productos
    private Usuario usuario;
    
    @ManyToOne // Relación con Categoria
    @JoinColumn(name = "id_cliente") // Columna en la tabla de productos
    private Cliente cliente;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private Integer estado = 1;

    @Column(nullable = false)
    private Integer apertura = 1;

    @Column(nullable = false)
    private Integer serie = 1;

    @Column(nullable = false)
    private Integer metodo;

    // Puedes agregar aquí un constructor adicional si lo necesitas
}
