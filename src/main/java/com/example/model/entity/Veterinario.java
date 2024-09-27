package com.example.model.entity;

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
}
