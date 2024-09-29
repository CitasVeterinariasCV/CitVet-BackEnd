package com.example.mapper;
import com.example.dto.Horario_DisponibilidadDTO;
import com.example.model.entity.Horario_Disponibilidad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HorarioDisponibilidadMapper {
    private final ModelMapper modelMapper;

    public HorarioDisponibilidadMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Horario_DisponibilidadDTO toDTO(Horario_Disponibilidad horarioDisponibilidad) {
        return modelMapper.map(horarioDisponibilidad, Horario_DisponibilidadDTO.class);
    }

    public Horario_Disponibilidad toEntity(Horario_DisponibilidadDTO horarioDisponibilidadDTO) {
        return modelMapper.map(horarioDisponibilidadDTO, Horario_Disponibilidad.class);
    }
}
