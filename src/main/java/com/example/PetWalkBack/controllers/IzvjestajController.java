package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Izvjestaj;
import com.example.PetWalkback.models.requests.IzvjestajRequest;
import com.example.PetWalkback.services.IzvjestajService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/izvjestaji")
public class IzvjestajController {
    private final IzvjestajService izvjestajService;
    public IzvjestajController(IzvjestajService izvjestajService) {
        this.izvjestajService = izvjestajService;
    }

    @GetMapping
    List<Izvjestaj> findAll() {
        return izvjestajService.findAll(Izvjestaj.class);
    }

    @GetMapping("/{id}")
    public Izvjestaj findById(@PathVariable Integer id) throws NotFoundException {
        return izvjestajService.findById(id, Izvjestaj.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        izvjestajService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Izvjestaj insert(@RequestBody IzvjestajRequest izvjestajRequest) throws NotFoundException {
        return izvjestajService.insert(izvjestajRequest, Izvjestaj.class);
    }

    @PutMapping("/{id}")
    public Izvjestaj update(@PathVariable Integer id, @Valid @RequestBody IzvjestajRequest izvjestajRequest) throws NotFoundException {
        return izvjestajService.update(id, izvjestajRequest, Izvjestaj.class);
    }

}
