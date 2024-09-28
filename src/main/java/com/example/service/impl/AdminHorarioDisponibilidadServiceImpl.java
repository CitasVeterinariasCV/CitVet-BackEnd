package com.example.service.impl;

import com.example.model.entity.Dueno;
import com.example.model.entity.Horario_Disponibilidad;
import com.example.model.entity.Veterinario;
import com.example.repository.HorarioDisponibilidadRepository;
import com.example.repository.VeterinarioRepository;
import com.example.service.AdminHorarioDisponibilidadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminHorarioDisponibilidadServiceImpl implements AdminHorarioDisponibilidadService {
    private final HorarioDisponibilidadRepository horarioDisponibilidadRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Horario_Disponibilidad> getAll() {
        return horarioDisponibilidadRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Horario_Disponibilidad> paginate(Pageable pageable) {
        return horarioDisponibilidadRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Horario_Disponibilidad findById(Integer id) {
        return horarioDisponibilidadRepository.findById(id).orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    @Transactional
    @Override
    public Horario_Disponibilidad create(Horario_Disponibilidad horarioDisponibilidad, Integer veterinarioId) {
        if (horarioDisponibilidad == null) {
            throw new IllegalArgumentException("El horario no puede ser nulo");
        }

        if (veterinarioId == null) {
            throw new IllegalArgumentException("El ID del veterinario no puede ser nulo");
        }

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new EntityNotFoundException("Veterinario no encontrado con ID: " + veterinarioId));

        horarioDisponibilidad.setVeterinario(veterinario);

        return horarioDisponibilidadRepository.save(horarioDisponibilidad);
    }

    @Transactional
    @Override
    public Horario_Disponibilidad update(Integer id, Horario_Disponibilidad updateHorarioDisponibilidad) {
        Horario_Disponibilidad horarioDisponibilidadFromDb = findById(id);
        horarioDisponibilidadFromDb.setHora_inicio(LocalDateTime.now());
        horarioDisponibilidadFromDb.setHora_fin(LocalDateTime.now());
        return horarioDisponibilidadRepository.save(horarioDisponibilidadFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Horario_Disponibilidad horarioDisponibilidad = findById(id);
        horarioDisponibilidadRepository.delete(horarioDisponibilidad);
    }
}
