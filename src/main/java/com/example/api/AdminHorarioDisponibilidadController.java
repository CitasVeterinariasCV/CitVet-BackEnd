package com.example.api;
import com.example.dto.Horario_DisponibilidadDTO;
import com.example.model.entity.Horario_Disponibilidad;
import com.example.service.AdminHorarioDisponibilidadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/horarios")
public class AdminHorarioDisponibilidadController {
    private final AdminHorarioDisponibilidadService adminHorarioDisponibilidadService;


    @GetMapping
    public ResponseEntity<List<Horario_Disponibilidad>> getAllCitas(){
        List<Horario_Disponibilidad> horarios = adminHorarioDisponibilidadService.getAll();
        return new ResponseEntity<List<Horario_Disponibilidad>>(horarios, HttpStatus.OK);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<Horario_Disponibilidad>> paginateCitas(
            @PageableDefault(size = 2, sort = "veterinario") Pageable pageable){
        Page<Horario_Disponibilidad> horarios = adminHorarioDisponibilidadService.paginate(pageable);
        return new ResponseEntity<Page<Horario_Disponibilidad>>(horarios,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario_Disponibilidad> getHorariosById(@PathVariable("id") Integer id){
        Horario_Disponibilidad horarioDisponibilidad = adminHorarioDisponibilidadService.findById(id);
        return new ResponseEntity<Horario_Disponibilidad>(horarioDisponibilidad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Horario_Disponibilidad> createHorario(@RequestBody Horario_DisponibilidadDTO horarioDisponibilidadDTO) {
        // Validación básica de campos nulos
        if (horarioDisponibilidadDTO == null ||
                horarioDisponibilidadDTO.getHora_inicio() == null ||
                horarioDisponibilidadDTO.getHora_fin() == null ||
                horarioDisponibilidadDTO.getVeterinarioId() == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Crear una nueva instancia de Horario_Disponibilidad
            Horario_Disponibilidad horarioDisponibilidad = new Horario_Disponibilidad();
            // Asignar los valores desde el DTO a la entidad
            horarioDisponibilidad.setHora_inicio(horarioDisponibilidadDTO.getHora_inicio());
            horarioDisponibilidad.setHora_fin(horarioDisponibilidadDTO.getHora_fin());

            // Llamar al servicio para crear el horario de disponibilidad
            Horario_Disponibilidad newHorario = adminHorarioDisponibilidadService.create(horarioDisponibilidad,horarioDisponibilidadDTO.getVeterinarioId());

            // Retornar la respuesta con el nuevo objeto creado
            return new ResponseEntity<>(newHorario, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Manejar el caso en que no se encuentra el veterinario
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            // Manejar el caso en que se pasa un argumento inválido
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejo general de excepciones
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Horario_Disponibilidad> updateHorario(@PathVariable("id") Integer id,
                                           @RequestBody Horario_Disponibilidad horarioDisponibilidad){
        Horario_Disponibilidad updateHorario = adminHorarioDisponibilidadService.update(id,horarioDisponibilidad);
        return new ResponseEntity<Horario_Disponibilidad>(updateHorario,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Horario_Disponibilidad> deleteCita(@PathVariable("id") Integer id){
        adminHorarioDisponibilidadService.delete(id);
        return new ResponseEntity<Horario_Disponibilidad>(HttpStatus.NO_CONTENT);
    }
}
