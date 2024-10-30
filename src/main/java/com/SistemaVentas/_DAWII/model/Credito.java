package com.SistemaVentas._DAWII.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "creditos")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto; // Utiliza BigDecimal para manejar valores decimales
    private Timestamp fecha; // Timestamp para manejar fecha y hora
    private int estado; // Estado del crédito
    @ManyToOne // Relación con Medida
    @JoinColumn(name = "idVenta") // Columna en la tabla de productos
    private Venta venta;

 

    // Puedes agregar constructores, métodos adicionales, etc. si es necesario
}
