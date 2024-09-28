package com.example.model.entity;

import com.example.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Veterinario")
@Data

public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Role rol;
}
