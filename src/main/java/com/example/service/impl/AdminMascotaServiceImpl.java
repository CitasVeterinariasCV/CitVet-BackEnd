package com.example.service.impl;

import com.example.model.entity.Mascota;
import com.example.model.entity.Dueno;
import com.example.repository.DuenoRepository;
import com.example.repository.MascotaRepository;
import com.example.service.AdminMascotaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class AdminMascotaServiceImpl implements AdminMascotaService {
    private final MascotaRepository mascotaRepository;
    private final DuenoRepository duenoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Mascota> getAll() {
        return mascotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Mascota> paginate(Pageable pageable) {
        return mascotaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Mascota findById(Integer id) {
        return mascotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    @Transactional
    @Override
    public Mascota create(Mascota mascota, Integer duenoId) {
        if (mascota == null) {
            throw new IllegalArgumentException("La moscota no puede ser nulo");
        }

        if (duenoId == null) {
            throw new IllegalArgumentException("El ID del dueño no puede ser nulo");
        }

        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new EntityNotFoundException("Dueño no encontrado con ID: " + duenoId));

        mascota.setDueno(dueno);

        return mascotaRepository.save(mascota);
    }

    @Transactional
    @Override
    public Mascota update(Integer id, Mascota updateMascota) {
        Mascota mascotaFromDb = findById(id);
        mascotaFromDb.setNombre(updateMascota.getNombre());
        mascotaFromDb.setEdad(updateMascota.getEdad());
        return mascotaRepository.save(mascotaFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Mascota mascota = findById(id);
        mascotaRepository.delete(mascota);
    }
}
