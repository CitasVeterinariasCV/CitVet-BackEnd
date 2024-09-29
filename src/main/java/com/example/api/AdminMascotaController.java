package com.example.api;

import com.example.dto.MascotaDTO;
import com.example.model.entity.Mascota;
import com.example.service.AdminMascotaService;
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
@RequestMapping("/admin/mascotas")
public class AdminMascotaController {
    private final AdminMascotaService adminMascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> getAllMascotas(){
        List<MascotaDTO> mascotas = adminMascotaService.getAll();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<MascotaDTO>> paginateMascotas(@PageableDefault(size = 2, sort = "nombre") Pageable pageable) {
        Page<MascotaDTO> mascotas = adminMascotaService.paginate(pageable);
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getMascotasById(@PathVariable("id") Integer id) {
        MascotaDTO mascota = adminMascotaService.findById(id);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/dueno/{duenoId}")
    public ResponseEntity<List<MascotaDTO>> getMascotaByDuenoId(@PathVariable("duenoId") Integer duenoId) {
        List<MascotaDTO> mascotas = adminMascotaService.getMascotaByDuenoId(duenoId);
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> createMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            MascotaDTO newMascota = adminMascotaService.create(mascotaDTO, mascotaDTO.getDuenoId());
            return new ResponseEntity<>(newMascota, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> updateMascota(@PathVariable("id") Integer id, @RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO updatedMascota = adminMascotaService.update(id, mascotaDTO);
        return ResponseEntity.ok(updatedMascota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable("id") Integer id) {
        adminMascotaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
