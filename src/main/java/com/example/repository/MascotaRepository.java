package com.example.repository;

import com.example.model.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByDuenoId(Integer duenoId);
}
