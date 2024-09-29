package com.example.service.impl;

import com.example.dto.CitaDTO;
import com.example.mapper.CitaMapper;
import com.example.model.entity.Cita;
import com.example.model.entity.Dueno;
import com.example.model.entity.Veterinario;
import com.example.model.enums.CitaStatus;
import com.example.repository.CitaRepository;
import com.example.repository.DuenoRepository;
import com.example.repository.VeterinarioRepository;
import com.example.service.AdminCitaService;
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
public class AdminCitaServiceImpl implements AdminCitaService {
    private final CitaRepository citaRepository;
    private final DuenoRepository duenoRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final CitaMapper citaMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CitaDTO> getAll() {
        List<Cita> citas = citaRepository.findAll();
        return citas.stream()
                .map(citaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CitaDTO> paginate(Pageable pageable) {
        Page<Cita> citas = citaRepository.findAll(pageable);
        return citas.map(citaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CitaDTO findById(Integer id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return citaMapper.toDTO(cita);
    }

    @Transactional
    @Override
    public CitaDTO create(CitaDTO citaDTO, Integer duenoId, Integer veterinarioId) {
        if (citaDTO == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }

        if (duenoId == null || veterinarioId == null) {
            throw new IllegalArgumentException("El ID del dueño o veterinario no puede ser nulo");
        }

        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new EntityNotFoundException("Dueño no encontrado con ID: " + duenoId));
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new EntityNotFoundException("Veterinario no encontrado con ID: " + veterinarioId));

        Cita cita = citaMapper.toEntity(citaDTO);
        cita.setEstado(CitaStatus.PENDIENTE);
        cita.setDueno(dueno);
        cita.setVeterinario(veterinario);

        Cita savedCita = citaRepository.save(cita);
        return citaMapper.toDTO(savedCita);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CitaDTO> getCitaByDuenoId(Integer duenoId) {
        List<Cita> citas = citaRepository.findByDuenoId(duenoId);
        return citas.stream()
                .map(citaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CitaDTO> getCitaByVeterinarioId(Integer veterinarioId) {
        List<Cita> citas = citaRepository.findByVeterinarioId(veterinarioId);
        return citas.stream()
                .map(citaMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public CitaDTO update(Integer id, CitaDTO updateCitaDTO) {
        Cita citaFromDb = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        citaFromDb.setEstado(updateCitaDTO.getEstado());
        Cita updatedCita = citaRepository.save(citaFromDb);
        return citaMapper.toDTO(updatedCita);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        citaRepository.delete(cita);
    }
}
