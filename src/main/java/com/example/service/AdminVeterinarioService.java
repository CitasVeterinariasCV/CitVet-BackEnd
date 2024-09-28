package com.example.service;

import com.example.model.entity.Veterinario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface AdminVeterinarioService {
    List<Veterinario> getAll();
    Page<Veterinario> paginate(Pageable pageable);
    Veterinario findById(Integer id);
    Veterinario create(Veterinario veterinario);
    Veterinario update(Integer id, Veterinario updateveterinario);
    void delete(Integer id);
}
