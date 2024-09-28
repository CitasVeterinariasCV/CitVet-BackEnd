package com.example.repository;

import com.example.model.entity.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
    boolean existsByCorreo(String correo);
}
