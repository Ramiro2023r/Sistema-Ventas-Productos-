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
import java.sql.Date;
import java.sql.Time;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "compras")
@EntityListeners(AuditingEntityListener.class)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "idProveedor") // Columna en la tabla de productos
    private Proveedor proveedor;
    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "idUsuario") // Columna en la tabla de productos
    private Usuario usuario;
    
    

  
    private BigDecimal total;
    private Date fecha;
    private Time hora;
    private int serie;
    private int estado = 1; // Valor por defecto
    private int metodo = 1; // Valor por defecto

    // Puedes agregar constructores y otros métodos si es necesario
}
