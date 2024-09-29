package com.example.service;

import com.example.model.entity.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMascotaService {
    List<Mascota> getAll();
    Page<Mascota> paginate(Pageable pageable);
    Mascota findById(Integer id);
    Mascota create(Mascota mascota,Integer duenoId);
    List<Mascota> getCitaByDuenoId(Integer duenoId);
    Mascota update(Integer id, Mascota updateMascota);
    void delete(Integer id);
}
