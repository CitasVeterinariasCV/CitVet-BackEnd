package com.example.api;

import com.example.model.entity.Dueno;
import com.example.service.AdminDuenoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dueno")
public class AdminDuenoController {
    private final AdminDuenoService adminDuenoService;

    @PostMapping("/register")
    public ResponseEntity<Dueno> register(@RequestBody Dueno dueno){
        Dueno newDueno = adminDuenoService.registerDueno(dueno);
        return new ResponseEntity<>(newDueno, HttpStatus.CREATED);
    }
}
