package com.SistemaVentas._DAWII.model;

import lombok.Data;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;

    private String direccion;

    @Column(nullable = false, columnDefinition = "varchar(50) default 'avatar.svg'")
    private String perfil = "avatar.svg";

    @Column(nullable = false)
    private String password;

    @Column(name = "id_caja", nullable = false)
    private Long idCaja;

    private String token;

    @Column(nullable = false, updatable = false)
    private Timestamp fecha;

    @Column(nullable = false)
    private Integer estado = 1;
}
