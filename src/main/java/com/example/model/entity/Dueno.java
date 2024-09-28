package com.example.model.entity;

import com.example.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Dueno")
@Data
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 60)
    private String apellido;

    @Column(name = "telefono", nullable = false, length = 60)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 60)
    private String direccion;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Role rol;

}
