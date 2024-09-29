package com.example.service;

import com.example.dto.MascotaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMascotaService {
    List<MascotaDTO> getAll();
    Page<MascotaDTO> paginate(Pageable pageable);
    MascotaDTO findById(Integer id);
    MascotaDTO create(MascotaDTO mascotaDTO,Integer duenoId);
    List<MascotaDTO> getMascotaByDuenoId(Integer duenoId);
    MascotaDTO update(Integer id, MascotaDTO updateMascotaDTO);
    void delete(Integer id);
}
