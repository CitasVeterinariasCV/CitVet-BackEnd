package com.example.api;

import com.example.dto.Horario_DisponibilidadDTO;
import com.example.service.AdminHorarioDisponibilidadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/horarios")
public class AdminHorarioDisponibilidadController {
    private final AdminHorarioDisponibilidadService adminHorarioDisponibilidadService;

    @GetMapping
    public ResponseEntity<List<Horario_DisponibilidadDTO>> getAllHorarios(){
        List<Horario_DisponibilidadDTO> horarios = adminHorarioDisponibilidadService.getAll();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Horario_DisponibilidadDTO>> paginateHorarios(@PageableDefault(size = 2, sort = "fecha") Pageable pageable) {
        Page<Horario_DisponibilidadDTO> horarios = adminHorarioDisponibilidadService.paginate(pageable);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario_DisponibilidadDTO> getHorariosById(@PathVariable("id") Integer id) {
        Horario_DisponibilidadDTO horario = adminHorarioDisponibilidadService.findById(id);
        return ResponseEntity.ok(horario);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<Horario_DisponibilidadDTO>> getHorarioByVeterinarioId(@PathVariable("veterinarioId") Integer veterinarioId) {
        List<Horario_DisponibilidadDTO> horarios = adminHorarioDisponibilidadService.getHorarioByVeterinarioId(veterinarioId);
        return ResponseEntity.ok(horarios);
    }

    @PostMapping
    public ResponseEntity<Horario_DisponibilidadDTO> createHorario(@RequestBody Horario_DisponibilidadDTO horarioDisponibilidadDTO) {
        try {
            Horario_DisponibilidadDTO newHorario = adminHorarioDisponibilidadService.create(horarioDisponibilidadDTO, horarioDisponibilidadDTO.getVeterinarioId());
            return new ResponseEntity<>(newHorario, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario_DisponibilidadDTO> updateHorario(@PathVariable("id") Integer id, @RequestBody Horario_DisponibilidadDTO horarioDisponibilidadDTO) {
        Horario_DisponibilidadDTO updatedHorario = adminHorarioDisponibilidadService.update(id, horarioDisponibilidadDTO);
        return ResponseEntity.ok(updatedHorario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable("id") Integer id) {
        adminHorarioDisponibilidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
