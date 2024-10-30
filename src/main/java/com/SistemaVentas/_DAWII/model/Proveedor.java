package com.SistemaVentas._DAWII.model;

import lombok.Data;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "proveedor")
@EntityListeners(AuditingEntityListener.class)
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruc;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Integer estado = 1;

    // Puedes agregar aquí un constructor, getters y setters si no usas Lombok
}
