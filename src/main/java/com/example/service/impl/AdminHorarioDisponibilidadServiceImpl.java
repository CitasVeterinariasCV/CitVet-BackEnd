package com.example.service.impl;

import com.example.dto.Horario_DisponibilidadDTO;
import com.example.mapper.HorarioDisponibilidadMapper;
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

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminHorarioDisponibilidadServiceImpl implements AdminHorarioDisponibilidadService {
    private final HorarioDisponibilidadRepository horarioDisponibilidadRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final HorarioDisponibilidadMapper horarioDisponibilidadMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Horario_DisponibilidadDTO> getAll() {
        List<Horario_Disponibilidad> horarios = horarioDisponibilidadRepository.findAll();
        return horarios.stream().map(horarioDisponibilidadMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Horario_DisponibilidadDTO> paginate(Pageable pageable) {
        Page<Horario_Disponibilidad> horarios = horarioDisponibilidadRepository.findAll(pageable);
        return horarios.map(horarioDisponibilidadMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Horario_DisponibilidadDTO findById(Integer id) {
        Horario_Disponibilidad horario = horarioDisponibilidadRepository.findById(id).orElseThrow(()
         -> new RuntimeException("Horario no encontrado"));
        return horarioDisponibilidadMapper.toDTO(horario);
    }

    @Transactional
    @Override
    public Horario_DisponibilidadDTO create(Horario_DisponibilidadDTO horarioDisponibilidadDTO, Integer veterinarioId) {
        if (horarioDisponibilidadDTO == null) {
            throw new IllegalArgumentException("El horario no puede ser nulo");
        }

        if (veterinarioId == null){
            throw new IllegalArgumentException("El Id del veterinario no puede ser nulo");
        }

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new EntityNotFoundException("Veterinario no encontrado con ID: " + veterinarioId));

        Horario_Disponibilidad horarioDisponibilidad = horarioDisponibilidadMapper.toEntity(horarioDisponibilidadDTO);
        horarioDisponibilidad.setVeterinario(veterinario);

        Horario_Disponibilidad savedHorario = horarioDisponibilidadRepository.save(horarioDisponibilidad);
        return horarioDisponibilidadMapper.toDTO(savedHorario);
    }

    @Transactional
    @Override
    public List<Horario_DisponibilidadDTO> getHorarioByVeterinarioId(Integer veterinarioId) {
        List<Horario_Disponibilidad> horarios = horarioDisponibilidadRepository.findByVeterinarioId(veterinarioId);
        return horarios.stream().map(horarioDisponibilidadMapper::toDTO).toList();
    }

    @Transactional
    @Override
    public Horario_DisponibilidadDTO update(Integer id, Horario_DisponibilidadDTO updateHorarioDisponibilidadDTO) {
        Horario_Disponibilidad horarioFromDb = horarioDisponibilidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        horarioFromDb.setHora_inicio(updateHorarioDisponibilidadDTO.getHora_inicio());
        horarioFromDb.setHora_fin(updateHorarioDisponibilidadDTO.getHora_fin());
        Horario_Disponibilidad updateHorario = horarioDisponibilidadRepository.save(horarioFromDb);
        return horarioDisponibilidadMapper.toDTO(updateHorario);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Horario_Disponibilidad horario = horarioDisponibilidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        horarioDisponibilidadRepository.delete(horario);
    }
}
