package com.example.api;

import com.example.dto.CitaDTO;
import com.example.model.entity.Cita;
import com.example.service.AdminCitaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/citas")
public class AdminCitaController {
    private final AdminCitaService adminCitaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas(){
        List<Cita> citas = adminCitaService.getAll();
        return new ResponseEntity<List<Cita>>(citas, HttpStatus.OK);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cita>> paginateCitas(
            @PageableDefault(size = 2, sort = "fecha")Pageable pageable){
        Page<Cita> citas = adminCitaService.paginate(pageable);
        return new ResponseEntity<Page<Cita>>(citas,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitasById(@PathVariable("id") Integer id){
        Cita cita = adminCitaService.findById(id);
        return new ResponseEntity<Cita>(cita, HttpStatus.OK);

    }

    @GetMapping("/dueno/{duenoId}")
    public ResponseEntity<List<CitaDTO>> getCitaByDuenoId(@PathVariable("duenoId") Integer duenoId) {
        List<Cita> citas = adminCitaService.getCitaByDuenoId(duenoId);

        // Convertir la lista de Cita a CitaDTO
        List<CitaDTO> citaDTOList = citas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(citaDTOList);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<CitaDTO>> getCitaByVeterinarioId(@PathVariable("veterinarioId") Integer veterinarioId){
        List<Cita> citas = adminCitaService.getCitaByVeterinarioId(veterinarioId);

        // Convertir la lista de Cita a CitaDTO
        List<CitaDTO> citaDTOList = citas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(citaDTOList);
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody CitaDTO citaDTO) {
            // Validación básica de campos nulos
        if (citaDTO == null || citaDTO.getFecha() == null ||
                citaDTO.getDuenoId() == null || citaDTO.getVeterinarioId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Crear la nueva entidad Cita
            Cita cita = new Cita();
            cita.setFecha(citaDTO.getFecha());
            cita.setDescripcion(citaDTO.getDescripcion());

            // Llamar al servicio para guardar la cita
            Cita newCita = adminCitaService.create(cita, citaDTO.getDuenoId(), citaDTO.getVeterinarioId());

            // Retornar la respuesta con el nuevo objeto creado
            return new ResponseEntity<>(newCita, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Manejar el caso en que no se encuentra el dueño o veterinario
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
    public ResponseEntity<Cita> updateCita(@PathVariable("id") Integer id,
                                           @RequestBody Cita cita){
        Cita updateCita = adminCitaService.update(id,cita);
        return new ResponseEntity<Cita>(updateCita,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cita> deleteCita(@PathVariable("id") Integer id){
        adminCitaService.delete(id);
        return new ResponseEntity<Cita>(HttpStatus.NO_CONTENT);
    }

    private CitaDTO convertToDTO(Cita cita) {
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setFecha(cita.getFecha());
        citaDTO.setDescripcion(cita.getDescripcion());
        citaDTO.setEstado(cita.getEstado());
        citaDTO.setDuenoId(cita.getDueno().getId());
        citaDTO.setVeterinarioId(cita.getVeterinario().getId());
        return citaDTO;
    }

}
