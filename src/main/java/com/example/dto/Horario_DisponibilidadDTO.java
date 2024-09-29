package com.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Horario_DisponibilidadDTO {
    private Integer id;

    @NotNull(message = "La hora de inicio es obligatoria!! ")
    private LocalDateTime hora_inicio;

    @NotNull(message = "La hora de fin es obligatoria!! ")
    private LocalDateTime hora_fin;

    @NotNull(message = "El Id del veterinario es obligatorio!! ")
    private Integer veterinarioId;
}
