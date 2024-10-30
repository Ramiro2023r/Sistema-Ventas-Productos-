package com.SistemaVentas._DAWII.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.Data;

@Data
@Entity
@Table(name = "cotizaciones")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext", nullable = false)
    private String productos;

    @Column(nullable = false, precision = 10, scale = 2)
    private double total;

    @Column(nullable = false)
    private java.sql.Date fecha;

    @Column(nullable = false)
    private java.sql.Time hora;

    @Column(nullable = false, length = 50)
    private String validez;

    @Column(columnDefinition = "longtext")
    private String comentario;
    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "id_cliente") // Columna en la tabla de productos
    private Cliente cliente;
    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "id_usuario") // Columna en la tabla de productos
    private Usuario usuario;
    
    


   

    // Puedes agregar constructores y otros métodos si es necesario
}
