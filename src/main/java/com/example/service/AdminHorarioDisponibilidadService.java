package com.example.service;

import com.example.model.entity.Horario_Disponibilidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminHorarioDisponibilidadService {
    List<Horario_Disponibilidad> getAll();
    Page<Horario_Disponibilidad> paginate(Pageable pageable);
    Horario_Disponibilidad findById(Integer id);
    Horario_Disponibilidad create(Horario_Disponibilidad horarioDisponibilidad,Integer veterinarioId);
    Horario_Disponibilidad update(Integer id, Horario_Disponibilidad updateHorarioDisponibilidad);
    void delete(Integer id);
}
