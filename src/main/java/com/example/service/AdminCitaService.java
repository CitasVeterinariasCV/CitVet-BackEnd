package com.example.service;

import com.example.model.entity.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCitaService {
    List<Cita> getAll();
    Page<Cita> paginate(Pageable pageable);
    Cita findById(Integer id);
    Cita create(Cita cita, Integer duenoId, Integer veterinarioId);
    List<Cita> getCitaByDuenoId(Integer duenoId);
    List<Cita> getCitaByVeterinarioId(Integer veterinarioId);
    Cita update(Integer id, Cita updatecita);
    void delete(Integer id);
}
