package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Recenzija;
import com.example.PetWalkback.models.requests.RecenzijaRequest;
import com.example.PetWalkback.services.RecenzijaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/recenzije")
public class RecenzijaController {

    private final RecenzijaService recenzijaService;

    public RecenzijaController(RecenzijaService recenzijaService) {
        this.recenzijaService = recenzijaService;
    }

    @GetMapping
    List<Recenzija> findAll() {
        return recenzijaService.findAll(Recenzija.class);
    }

    @GetMapping("/{id}")
    public Recenzija findById(@PathVariable Integer id) throws NotFoundException {
        return recenzijaService.findById(id, Recenzija.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        recenzijaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recenzija insert(@RequestBody RecenzijaRequest recenzijaRequest) throws NotFoundException {
        return recenzijaService.insert(recenzijaRequest, Recenzija.class);
    }

    @PutMapping("/{id}")
    public Recenzija update(@PathVariable Integer id, @Valid @RequestBody RecenzijaRequest recenzijaRequest) throws NotFoundException {
        return recenzijaService.update(id, recenzijaRequest, Recenzija.class);
    }

    @GetMapping("/ocjena")
    public List<Recenzija> sortByOcjena()
    {
        List<Recenzija> recenzija = recenzijaService.findAll(Recenzija.class);
        Collections.sort(recenzija, Comparator.comparing(Recenzija::getOcjena));
        return recenzija;
    }


}
