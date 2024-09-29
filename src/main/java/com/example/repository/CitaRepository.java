package com.example.repository;

import com.example.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByDuenoId(Integer duenoId);
    List<Cita> findByVeterinarioId(Integer veterinarioId);
    //List<Cita> findByVeterinarioIdAndEstado(Integer veterinarioId, String estado);
}
