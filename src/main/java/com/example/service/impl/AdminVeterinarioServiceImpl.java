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

    @Transactional
    @Override
    public Veterinario registerVeterinario(Veterinario veterinario) {
        if(veterinarioRepository.existsByCorreo(veterinario.getCorreo())){
            throw new RuntimeException("El correo ya está registrado");
        }
        return veterinarioRepository.save(veterinario);
    }
}
