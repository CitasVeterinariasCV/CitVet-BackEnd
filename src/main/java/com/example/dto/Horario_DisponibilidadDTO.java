package com.example.dto;
import com.example.model.entity.Veterinario;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class Horario_DisponibilidadDTO {
    private LocalDateTime hora_inicio;
    private LocalDateTime hora_fin;
    private Integer veterinarioId;
}
