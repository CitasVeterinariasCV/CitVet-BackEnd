package com.example.mapper;

import com.example.dto.MascotaDTO;
import com.example.model.entity.Mascota;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {
    private final ModelMapper modelMapper;

    public MascotaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MascotaDTO toDTO(Mascota mascota) {
        return modelMapper.map(mascota, MascotaDTO.class);
    }

    public Mascota toEntity(MascotaDTO mascotaDTO) {
        return modelMapper.map(mascotaDTO, Mascota.class);
    }
}
