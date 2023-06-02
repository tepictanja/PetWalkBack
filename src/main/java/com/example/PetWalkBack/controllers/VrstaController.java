package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Lokacija;
import com.example.PetWalkback.models.dto.Vrsta;
import com.example.PetWalkback.models.requests.VrstaRequest;
import com.example.PetWalkback.services.VrstaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vrste")
public class VrstaController {

    private final VrstaService vrstaService;

    public VrstaController(VrstaService vrstaService) {
        this.vrstaService = vrstaService;
    }

    @GetMapping
    public List<Vrsta> findAll() {
        return vrstaService.findAll(Vrsta.class);
    }

    @GetMapping("/{id}")
    public Vrsta findById(@PathVariable Integer id) throws NotFoundException {
        return vrstaService.findById(id, Vrsta.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        vrstaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vrsta insert(@RequestBody @Valid VrstaRequest vrstaRequest) throws NotFoundException {
        return vrstaService.insert(vrstaRequest, Vrsta.class);
    }

    @PutMapping("/{id}")
    public Vrsta update(@PathVariable Integer id, @Valid @RequestBody VrstaRequest vrstaRequest) throws NotFoundException {
        return vrstaService.update(id, vrstaRequest, Vrsta.class);
    }

}
