package com.example.service.impl;

import com.example.model.entity.Cita;
import com.example.model.entity.Veterinario;
import com.example.repository.VeterinarioRepository;
import com.example.service.AdminVeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminVeterinarioServiceImpl implements AdminVeterinarioService {
    private final VeterinarioRepository veterinarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Veterinario> getAll() {
        return veterinarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Veterinario> paginate(Pageable pageable) {
        return veterinarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Veterinario findById(Integer id) {
        return veterinarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    @Transactional
    @Override
    public Veterinario create(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Transactional
    @Override
    public Veterinario update(Integer id, Veterinario updateveterinario) {
        Veterinario veterinarioFromDb = findById(id);
        veterinarioFromDb.setNombre(updateveterinario.getNombre());
        veterinarioFromDb.setApellido(updateveterinario.getApellido());
        veterinarioFromDb.setEspecialidad(updateveterinario.getEspecialidad());
        return veterinarioRepository.save(veterinarioFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Veterinario veterinario = findById(id);
        veterinarioRepository.delete(veterinario);
    }
}
