package com.example.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Horario_Disponibilidad")
@Data
public class Horario_Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime hora_inicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalDateTime hora_fin;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Horario_Veterinario"))
    private Veterinario veterinario;
}
