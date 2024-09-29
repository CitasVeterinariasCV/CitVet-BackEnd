package com.example.mapper;

import com.example.dto.CitaDTO;
import com.example.model.entity.Cita;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {
    private final ModelMapper modelMapper;

    public CitaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public CitaDTO toDTO (Cita cita){
        return modelMapper.map(cita, CitaDTO.class);
    }

    public Cita toEntity(CitaDTO citaDTO){
        return modelMapper.map(citaDTO, Cita.class);
    }
}
