package com.example.PetWalkback.controllers;

import com.example.PetWalkback.models.dto.Usluga;
import com.example.PetWalkback.models.requests.UslugaRequest;
import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.UslugaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.PetWalkback.exceptions.NotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/usluge")
public class UslugaController {
    private final UslugaService uslugaService;
    private final KorisnikService korisnikService;

    public UslugaController(UslugaService uslugaService, KorisnikService korisnikService) {
        this.uslugaService = uslugaService;
        this.korisnikService = korisnikService;
    }

    @GetMapping
    List<Usluga> findAll() {
        return uslugaService.findAll(Usluga.class);
    }

    @GetMapping("/{id}")
    public Usluga findById(@PathVariable Integer id) throws NotFoundException {
        return uslugaService.findById(id, Usluga.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        uslugaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usluga insert(@RequestBody UslugaRequest uslugaRequest) throws NotFoundException {
        return uslugaService.insert(uslugaRequest, Usluga.class);
    }

    @PutMapping("/{id}")
    public Usluga update(@PathVariable Integer id, @Valid @RequestBody UslugaRequest uslugaRequest) throws NotFoundException {
        return uslugaService.update(id, uslugaRequest, Usluga.class);
    }

    @GetMapping("/naziv")
    public List<Usluga> sortByNazivUsluge()
    {
        List<Usluga> usluge = uslugaService.findAll(Usluga.class);
        Collections.sort(usluge, Comparator.comparing(Usluga::getNaziv));
        return usluge;
    }
}
