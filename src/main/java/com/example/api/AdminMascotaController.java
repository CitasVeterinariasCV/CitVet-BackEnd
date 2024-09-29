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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/mascotas")
public class AdminMascotaController {
    private final AdminMascotaService adminMascotaService;

    @GetMapping
    public ResponseEntity<List<Mascota>> getAllMascotas(){
        List<Mascota> mascotas = adminMascotaService.getAll();
        return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Mascota>> paginateMascotas(
            @PageableDefault(size = 2, sort = "nombre") Pageable pageable){
        Page<Mascota> mascotas = adminMascotaService.paginate(pageable);
        return new ResponseEntity<Page<Mascota>>(mascotas,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotasById(@PathVariable("id") Integer id){
        Mascota mascota = adminMascotaService.findById(id);
        return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
    }

    @GetMapping("/dueno/{duenoId}")
    public ResponseEntity<List<MascotaDTO>> getMascotaByDuenoId(@PathVariable("duenoId") Integer duenoId) {
        List<Mascota> mascotas = adminMascotaService.getCitaByDuenoId(duenoId);

        // Convertir la lista de Cita a CitaDTO
        List<MascotaDTO> mascotaDTOList = mascotas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(mascotaDTOList);
    }

    @PostMapping
    public ResponseEntity<Mascota> createMascota(@RequestBody MascotaDTO mascotaDTO) {
        // Validación básica de campos nulos
        if (mascotaDTO == null ||
                mascotaDTO.getNombre() == null ||
                mascotaDTO.getEspecie() == null ||
                mascotaDTO.getRaza() == null ||
                mascotaDTO.getEdad() == null ||
                mascotaDTO.getDuenoId() == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Crear una nueva instancia de Horario_Disponibilidad
            Mascota mascota = new Mascota();
            // Asignar los valores desde el DTO a la entidad
            mascota.setNombre(mascotaDTO.getNombre());
            mascota.setEspecie(mascotaDTO.getEspecie());
            mascota.setRaza(mascotaDTO.getRaza());
            mascota.setEdad(mascotaDTO.getEdad());

            // Llamar al servicio para crear el horario de disponibilidad
            Mascota newMascota = adminMascotaService.create(mascota,mascotaDTO.getDuenoId());
            // Retornar la respuesta con el nuevo objeto creado
            return new ResponseEntity<>(newMascota, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Manejar el caso en que no se encuentra el dueño
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
    public ResponseEntity<Mascota> updateMascota(@PathVariable("id") Integer id,
                                                                @RequestBody Mascota mascota){
        Mascota updateMascota = adminMascotaService.update(id,mascota);
        return new ResponseEntity<Mascota>(updateMascota,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mascota> deleteMascota(@PathVariable("id") Integer id){
        adminMascotaService.delete(id);
        return new ResponseEntity<Mascota>(HttpStatus.NO_CONTENT);
    }

    // Método de conversión de Mascota a MascotaDTO
    private MascotaDTO convertToDTO(Mascota mascota) {
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setNombre(mascota.getNombre());
        mascotaDTO.setEspecie(mascota.getEspecie());
        mascotaDTO.setRaza(mascota.getRaza());
        mascotaDTO.setEdad(mascota.getEdad());
        mascotaDTO.setDuenoId(mascota.getDueno().getId()); // Acceder al ID del dueño
        return mascotaDTO;
    }

}
