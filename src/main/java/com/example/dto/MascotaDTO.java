package com.example.dto;
import com.example.model.entity.Dueno;
import lombok.Data;
@Data
public class MascotaDTO {
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Integer duenoId;
}
