package com.example.PetWalkback.controllers;

import com.example.PetWalkback.models.dto.Ponuda;
import com.example.PetWalkback.models.requests.PonudaRequest;
import com.example.PetWalkback.services.PonudaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.PetWalkback.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/ponude")
public class PonudaController {
    private final PonudaService ponudaService;
    public PonudaController(PonudaService ponudaService) {
        this.ponudaService = ponudaService;
    }

    @GetMapping
    List<Ponuda> findAll() {
        return ponudaService.findAll(Ponuda.class);
    }

    @GetMapping("/{id}")
    public Ponuda findById(@PathVariable Integer id) throws NotFoundException {
        return ponudaService.findById(id, Ponuda.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ponudaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ponuda insert(@RequestBody PonudaRequest ponudaRequest) throws NotFoundException {
        return ponudaService.insert(ponudaRequest, Ponuda.class);
    }

    @PutMapping("/{id}")
    public Ponuda update(@PathVariable Integer id, @Valid @RequestBody PonudaRequest ponudaRequest) throws NotFoundException {
        return ponudaService.update(id, ponudaRequest, Ponuda.class);
    }
}
