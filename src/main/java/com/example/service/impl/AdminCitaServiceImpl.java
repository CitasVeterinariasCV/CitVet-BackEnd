package com.example.service.impl;

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

    @Transactional(readOnly = true)
    @Override
    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> paginate(Pageable pageable) {

        return citaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Cita findById(Integer id) {
        return citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Transactional
    @Override
    public Cita create(Cita cita, Integer duenoId, Integer veterinarioId) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }

        if (duenoId == null || veterinarioId == null) {
            throw new IllegalArgumentException("El ID del dueño o veterinario no puede ser nulo");
        }

        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new EntityNotFoundException("Dueño no encontrado con ID: " + duenoId));
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new EntityNotFoundException("Veterinario no encontrado con ID: " + veterinarioId));

        cita.setEstado(CitaStatus.PENDIENTE);
        cita.setDueno(dueno);
        cita.setVeterinario(veterinario);

        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> getCitaByDuenoId(Integer duenoId) {
        return citaRepository.findByDuenoId(duenoId);
    }

    @Override
    public List<Cita> getCitaByVeterinarioId(Integer veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId);
    }

    @Transactional
    @Override
    public Cita update(Integer id, Cita updatecita) {
        Cita citaFromDb = findById(id);
        citaFromDb.setEstado(updatecita.getEstado());
        return citaRepository.save(citaFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Cita cita = findById(id);
        citaRepository.delete(cita);

    }
}
