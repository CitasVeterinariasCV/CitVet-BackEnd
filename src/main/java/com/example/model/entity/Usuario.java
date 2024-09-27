package com.example.model.entity;

import com.example.model.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Entity
@Table(name = "Usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Role rol;
}
