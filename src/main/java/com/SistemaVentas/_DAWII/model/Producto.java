package com.SistemaVentas._DAWII.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
    private String codigo;
    private String descripcion;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private BigDecimal cantidad;
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "idMedida") // Columna en la tabla de productos
    private Medida medida;

    @ManyToOne // Relación con Categoria
    @JoinColumn(name = "idCategoria") // Columna en la tabla de productos
    private Categoria categoria;
    private String foto;
    private int estado;
    private Timestamp fecha;
			
}
