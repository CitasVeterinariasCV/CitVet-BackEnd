package com.example.repository;

import com.example.model.entity.Horario_Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioDisponibilidadRepository extends JpaRepository<Horario_Disponibilidad, Integer> {
    List<Horario_Disponibilidad> findByVeterinarioId(Integer veterinarioId);
}
