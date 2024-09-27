package com.example.model.entity;

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

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Dueno_Usuario"))
    private Usuario usuario;
}
