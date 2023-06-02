package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.*;
import com.example.PetWalkback.models.enums.Role;
import com.example.PetWalkback.models.requests.CijenaRequest;
import com.example.PetWalkback.services.CijenaService;
import javax.validation.Valid;

import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.UslugaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/cijene")
public class CijenaController {
    private final CijenaService cijenaService;
    private final KorisnikService korisnikService;
    private final UslugaService uslugaService;

    public CijenaController(CijenaService cijenaService, KorisnikService korisnikService, UslugaService uslugaService) {
        this.cijenaService = cijenaService;
        this.korisnikService = korisnikService;
        this.uslugaService = uslugaService;
    }


    @GetMapping
    List<Cijena> findAll() {
        return cijenaService.findAll(Cijena.class);
    }

    @GetMapping("/{id}")
    public Cijena findById(@PathVariable Integer id) throws NotFoundException {
        return cijenaService.findById(id, Cijena.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        cijenaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cijena insert(@RequestBody CijenaRequest cijenaRequest) throws NotFoundException {
        return cijenaService.insert(cijenaRequest, Cijena.class);
    }

    @PutMapping("/{id}")
    public Cijena update(@PathVariable Integer id, @Valid @RequestBody CijenaRequest cijenaRequest) throws NotFoundException {
        return cijenaService.update(id, cijenaRequest, Cijena.class);
    }
   @GetMapping("/cijena")
    public  List<Cijena> sortByUslugaPoCijeni()
    {
        List<Cijena> cijena=cijenaService.findAll(Cijena.class);
        Collections.sort(cijena, Comparator.comparing(Cijena::getCijena));
        return cijena;
    }

    @GetMapping("cijenaKorisnik/{naziv}")
    public List<Korisnik> filterKorisnikUsluga(@PathVariable String naziv)
    {
        List<Korisnik> sviKorisnici = korisnikService.findAll(Korisnik.class);
        List<Usluga> sveUsluge= uslugaService.findAll(Usluga.class);
        List<Cijena> sveCijene= cijenaService.findAll(Cijena.class);
        List<Korisnik> filtriraniKorisnici= new ArrayList<>();

       for( Korisnik korisnik : sviKorisnici)
          for(Usluga usluga: sveUsluge)
          for (Cijena cijena : sveCijene)
            {
                if( korisnik.getRole().equals(Role.CUVAR) && usluga.getNaziv().equals(naziv) && cijena.getKorisnikId().equals(korisnik.getId()) && cijena.getUslugaId()==usluga.getId())
                    filtriraniKorisnici.add(korisnik);
            }
        return  filtriraniKorisnici;
    }

}
