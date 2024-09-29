package com.example.service.impl;

import com.example.dto.MascotaDTO;
import com.example.mapper.MascotaMapper;
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
    private final MascotaMapper mascotaMapper;
    private final DuenoRepository duenoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MascotaDTO> getAll() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return mascotas.stream().map(mascotaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MascotaDTO> paginate(Pageable pageable) {
        Page<Mascota> mascotas = mascotaRepository.findAll(pageable);
        return mascotas.map(mascotaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public MascotaDTO findById(Integer id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Mascota no encontrada"));
        return mascotaMapper.toDTO(mascota);
    }

    @Transactional
    @Override
    public MascotaDTO create(MascotaDTO mascotaDTO, Integer duenoId) {
        if (mascotaDTO == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }

        if (duenoId == null){
            throw new IllegalArgumentException("El Id del dueño no puede ser nulo");
        }

        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new EntityNotFoundException("Dueño no encontrado con ID: " + duenoId));

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        mascota.setDueno(dueno);

        Mascota savedMascota = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(savedMascota);
    }

    @Transactional
    @Override
    public List<MascotaDTO> getMascotaByDuenoId(Integer duenoId) {
        List<Mascota> mascotas = mascotaRepository.findByDuenoId(duenoId);
        return mascotas.stream().map(mascotaMapper::toDTO).toList();
    }

    @Transactional
    @Override
    public MascotaDTO update(Integer id, MascotaDTO updateMascotaDTO) {
        Mascota mascotaFromDb = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        mascotaFromDb.setNombre(updateMascotaDTO.getNombre());
        mascotaFromDb.setEdad(updateMascotaDTO.getEdad());

        Mascota updateMascota = mascotaRepository.save(mascotaFromDb);
        return mascotaMapper.toDTO(updateMascota);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        mascotaRepository.delete(mascota);
    }
}
