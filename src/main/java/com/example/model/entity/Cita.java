package com.example.model.entity;

import com.example.model.enums.CitaStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
@Data

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private CitaStatus estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Cita_Usuario"))
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veterinario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Cita_Veterinario"))
    private Veterinario veterinario;
}
