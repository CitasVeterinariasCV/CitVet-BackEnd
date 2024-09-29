package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MascotaDTO {
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio!! ")
    @Size(max = 50, message = "El nombre debe ser de 50 caracteres o  menos")
    private String nombre;

    @NotBlank(message = "La especie es obligatorio!! ")
    @Size(max = 50, message = "La especie debe ser de 50 caracteres o  menos")
    private String especie;

    @NotBlank(message = "La raza es obligatorio!! ")
    @Size(max = 50, message = "La raza debe ser de 50 caracteres o  menos")
    private String raza;

    @NotNull(message = "La edad de la mascota es obligatorio!! ")
    private Integer edad;

    @NotNull(message = "El Id del dueño es obligatorio!! ")
    private Integer duenoId;
}
