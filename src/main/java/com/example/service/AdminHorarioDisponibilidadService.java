package com.example.service;

import com.example.dto.Horario_DisponibilidadDTO;
import com.example.model.entity.Horario_Disponibilidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminHorarioDisponibilidadService {
    List<Horario_DisponibilidadDTO> getAll();
    Page<Horario_DisponibilidadDTO> paginate(Pageable pageable);
    Horario_DisponibilidadDTO findById(Integer id);
    Horario_DisponibilidadDTO create(Horario_DisponibilidadDTO horarioDisponibilidadDTO, Integer veterinarioId);
    List<Horario_DisponibilidadDTO> getHorarioByVeterinarioId(Integer veterinarioId);
    Horario_DisponibilidadDTO update(Integer id, Horario_DisponibilidadDTO updateHorarioDisponibilidadDTO);
    void delete(Integer id);
}
