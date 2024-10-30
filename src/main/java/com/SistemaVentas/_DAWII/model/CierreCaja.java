package com.SistemaVentas._DAWII.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cierre_caja")
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "id_usuario") // Columna en la tabla de productos
    private Usuario usuario;


    
    private BigDecimal monto_inicial; // Mapea a la columna `monto_inicial`
    
    private Date fecha_apertura; // Mapea a la columna `fecha_apertura`
    
    private Date fecha_cierre; // Mapea a la columna `fecha_cierre`, puede ser NULL
    
    private BigDecimal monto_final; // Mapea a la columna `monto_final`
    
    private int total_ventas; // Mapea a la columna `total_ventas`
    
    private BigDecimal monto_total; // Mapea a la columna `monto_total`
    
    private int estado; // Mapea a la columna `estado`

    // Puedes agregar constructores, getters y setters si lo necesitas,
    // pero @Data de Lombok ya genera estos métodos automáticamente.
}
