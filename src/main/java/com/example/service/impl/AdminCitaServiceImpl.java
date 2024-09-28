package com.example.service.impl;

import com.example.model.entity.Cita;
import com.example.repository.CitaRepository;
import com.example.service.AdminCitaService;
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
    public Cita create(Cita cita) {
        return citaRepository.save(cita);
    }

    @Transactional
    @Override
    public Cita update(Integer id, Cita updatecita) {
        Cita citaFromDb = findById(id);
        citaFromDb.setFecha(LocalDateTime.now());
        citaFromDb.setDescripcion(updatecita.getDescripcion());
        citaFromDb.setEstado(updatecita.getEstado());
        citaFromDb.setVeterinario(updatecita.getVeterinario());
        return citaRepository.save(citaFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Cita cita = findById(id);
        citaRepository.delete(cita);

    }
}
