package com.example.api;

import com.example.model.entity.Cita;
import com.example.service.AdminCitaService;
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

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita){
        Cita newCita = adminCitaService.create(cita);
        return new ResponseEntity<Cita>(newCita,HttpStatus.CREATED);

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

}
