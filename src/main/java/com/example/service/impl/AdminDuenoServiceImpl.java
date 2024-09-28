package com.example.service.impl;

import com.example.model.entity.Dueno;
import com.example.repository.DuenoRepository;
import com.example.service.AdminDuenoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminDuenoServiceImpl implements AdminDuenoService {
    private final DuenoRepository duenoRepository;

    @Transactional
    @Override
    public Dueno registerDueno(Dueno dueno) {
        if(duenoRepository.existsByCorreo(dueno.getCorreo())){
            throw new RuntimeException("El correo ya está registrado");
        }
        return duenoRepository.save(dueno);
    }
}
