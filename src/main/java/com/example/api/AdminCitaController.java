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
    public ResponseEntity<List<CitaDTO>> getAllCitas(){
        List<CitaDTO> citas = adminCitaService.getAll();
        return ResponseEntity.ok(citas);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<CitaDTO>> paginateCitas(@PageableDefault(size = 2, sort = "fecha") Pageable pageable) {
        Page<CitaDTO> citas = adminCitaService.paginate(pageable);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getCitasById(@PathVariable("id") Integer id) {
        CitaDTO cita = adminCitaService.findById(id);
        return ResponseEntity.ok(cita);
    }

    @GetMapping("/dueno/{duenoId}")
    public ResponseEntity<List<CitaDTO>> getCitaByDuenoId(@PathVariable("duenoId") Integer duenoId) {
        List<CitaDTO> citas = adminCitaService.getCitaByDuenoId(duenoId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<CitaDTO>> getCitaByVeterinarioId(@PathVariable("veterinarioId") Integer veterinarioId) {
        List<CitaDTO> citas = adminCitaService.getCitaByVeterinarioId(veterinarioId);
        return ResponseEntity.ok(citas);
    }

    @PostMapping
    public ResponseEntity<CitaDTO> createCita(@RequestBody CitaDTO citaDTO) {
        try {
            CitaDTO newCita = adminCitaService.create(citaDTO, citaDTO.getDuenoId(), citaDTO.getVeterinarioId());
            return new ResponseEntity<>(newCita, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> updateCita(@PathVariable("id") Integer id, @RequestBody CitaDTO citaDTO) {
        CitaDTO updatedCita = adminCitaService.update(id, citaDTO);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable("id") Integer id) {
        adminCitaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
