package com.example.repository;

import com.example.model.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
