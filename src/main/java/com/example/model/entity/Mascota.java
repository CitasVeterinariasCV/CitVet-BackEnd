package com.example.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Mascota")
@Data
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "especie", nullable = false, length = 20)
    private String especie;

    @Column(name = "raza", nullable = false, length = 20)
    private String raza;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "dueno_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Mascota_Dueno"))
    private Dueno dueno;
}
