package com.example.dto;
import com.example.model.enums.CitaStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public class CitaDTO {
    private Integer id;

    @NotNull(message = "La fecha es obligatoria!! ")
    private LocalDateTime fecha;

    @NotBlank(message = "La descripcion es obligatoria!! ")
    @Size(max = 200, message = "La descripcion deber ser de 200 caracteres o  menos")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio!! ")
    private CitaStatus estado;

    @NotNull(message = "El Id del dueño es obligatorio!! ")
    private Integer duenoId;

    @NotNull(message = "El Id del veterinario es obligatorio!! ")
    private Integer veterinarioId;
}
