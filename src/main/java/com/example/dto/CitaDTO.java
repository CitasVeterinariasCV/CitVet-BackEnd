package com.example.dto;
import com.example.model.enums.CitaStatus;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CitaDTO {
    private LocalDateTime fecha;
    private String descripcion;
    private CitaStatus estado;
    private Integer duenoId;
    private Integer veterinarioId;
}
