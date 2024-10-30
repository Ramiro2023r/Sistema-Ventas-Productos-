package com.SistemaVentas._DAWII.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "configuracion")
public class Configuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruc;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String mensaje;
    private String logo;
    private int moneda;
    private int impuesto;
    private int cant_factura;
    private String site;

    // Puedes agregar constructores y otros métodos si es necesario
}
