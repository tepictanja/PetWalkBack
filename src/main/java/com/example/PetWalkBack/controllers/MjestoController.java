package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Mjesto;
import com.example.PetWalkback.models.requests.MjestoRequest;
import com.example.PetWalkback.services.MjestoService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mjesta")
public class MjestoController {
    private final MjestoService mjestoService;
    public MjestoController(MjestoService mjestoService) {
        this.mjestoService = mjestoService;
    }

    @GetMapping
    List<Mjesto> findAll() {
        return mjestoService.findAll(Mjesto.class);
    }

    @GetMapping("/{id}")
    public Mjesto findById(@PathVariable Integer id) throws NotFoundException {
        return mjestoService.findById(id, Mjesto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mjestoService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mjesto insert(@RequestBody MjestoRequest mjestoRequest) throws NotFoundException {
        return mjestoService.insert(mjestoRequest, Mjesto.class);
    }

    @PutMapping("/{id}")
    public Mjesto update(@PathVariable Integer id, @Valid @RequestBody MjestoRequest mjestoRequest) throws NotFoundException {
        return mjestoService.update(id, mjestoRequest, Mjesto.class);
    }

}
