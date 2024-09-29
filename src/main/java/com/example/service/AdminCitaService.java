package com.example.service;

import com.example.dto.CitaDTO;
import com.example.model.entity.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCitaService {
    List<CitaDTO> getAll();
    Page<CitaDTO> paginate(Pageable pageable);
    CitaDTO findById(Integer id);
    CitaDTO create(CitaDTO citaDTO, Integer duenoId, Integer veterinarioId);
    List<CitaDTO> getCitaByDuenoId(Integer duenoId);
    List<CitaDTO> getCitaByVeterinarioId(Integer veterinarioId);
    CitaDTO update(Integer id, CitaDTO updatecitaDTO);
    void delete(Integer id);
}
