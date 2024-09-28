package com.example.api;

import com.example.model.entity.Veterinario;
import com.example.service.AdminVeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veterinario")
public class AdminVeterinarioController {
    private final AdminVeterinarioService adminVeterinarioService;

    @PostMapping("/register")
    public ResponseEntity<Veterinario> register(@RequestBody Veterinario veterinario){
        Veterinario newVeterinario = adminVeterinarioService.registerVeterinario(veterinario);
        return new ResponseEntity<>(newVeterinario, HttpStatus.CREATED);
    }
}
