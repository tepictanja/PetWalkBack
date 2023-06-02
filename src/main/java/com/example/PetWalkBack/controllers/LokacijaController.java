package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.*;
import com.example.PetWalkback.models.enums.Role;
import com.example.PetWalkback.models.requests.LokacijaRequest;
import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.LjubimacService;
import com.example.PetWalkback.services.LokacijaService;
import javax.validation.Valid;
import com.example.PetWalkback.services.MjestoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lokacije")
public class LokacijaController {
    private final LokacijaService lokacijaService;
    private final MjestoService mjestoService;
    private final KorisnikService korisnikService;
    private final LjubimacService ljubimacService;

    public LokacijaController(LokacijaService lokacijaService, MjestoService mjestoService, KorisnikService korisnikService, LjubimacService ljubimacService) {
        this.lokacijaService = lokacijaService;
        this.mjestoService = mjestoService;
        this.korisnikService = korisnikService;
        this.ljubimacService = ljubimacService;
    }

    @GetMapping
    List<Lokacija> findAll() {
        return lokacijaService.findAll(Lokacija.class);
    }

    @GetMapping("/{id}")
    public Lokacija findById(@PathVariable Integer id) throws NotFoundException {
        return lokacijaService.findById(id, Lokacija.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        lokacijaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lokacija insert(@RequestBody LokacijaRequest lokacijaRequest) throws NotFoundException {
        return lokacijaService.insert( lokacijaRequest, Lokacija.class);
    }

    @PutMapping("/{id}")
    public Lokacija update(@PathVariable Integer id, @Valid @RequestBody LokacijaRequest lokacijaRequest) throws NotFoundException {
        return lokacijaService.update(id, lokacijaRequest, Lokacija.class);
    }

    @GetMapping("/trazenoMjesto/{trazenoMjesto}")
    public List<Korisnik> filtrirajCuvarePoLokaciji( @PathVariable String trazenoMjesto)
    {
        List<Korisnik> filtriraniKorisnici = new ArrayList<>();
        List<Korisnik> sviKorisnici = korisnikService.findAll(Korisnik.class);
        List<Lokacija> sveLokacije=lokacijaService.findAll(Lokacija.class);
        List<Mjesto> svaMjesta=mjestoService.findAll(Mjesto.class);

        for (Korisnik korisnik : sviKorisnici)
            for(Lokacija lokacija :sveLokacije)
                for(Mjesto mjesta : svaMjesta)
            {
            if (korisnik.getRole() == Role.CUVAR  &&  mjesta.getNaziv().equals(trazenoMjesto) && mjesta.getId()==lokacija.getMjestoId() && korisnik.getId()== lokacija.getKorisnikId()) {
                filtriraniKorisnici.add(korisnik);
            }
        }

        return filtriraniKorisnici;
    }

    @GetMapping("/trazenoMjestoLjubimac/{trazenoMjesto}")
    public List<Ljubimac> filtrirajVlasnikePoLokaciji( @PathVariable String trazenoMjesto)
    {
        List<Korisnik> filtriraniKorisnici = new ArrayList<>();
        List<Korisnik> sviKorisnici = korisnikService.findAll(Korisnik.class);
        List<Lokacija> sveLokacije=lokacijaService.findAll(Lokacija.class);
        List<Mjesto> svaMjesta=mjestoService.findAll(Mjesto.class);
        List<Ljubimac> sviLjubimci=ljubimacService.findAll(Ljubimac.class);
        List<Ljubimac> filtriraniLjubimci = new ArrayList<>();

        for (Korisnik korisnik : sviKorisnici)
            for(Lokacija lokacija :sveLokacije)
                for(Mjesto mjesta : svaMjesta)

                {
                    if (korisnik.getRole() == Role.VLASNIK  &&  mjesta.getNaziv().equals(trazenoMjesto) && mjesta.getId()==lokacija.getMjestoId() && korisnik.getId()== lokacija.getKorisnikId()) {
                        filtriraniKorisnici.add(korisnik);
                    }
                }
        for(Ljubimac ljubimac: sviLjubimci)
        for(Korisnik korisnik: filtriraniKorisnici)
        {
            if(ljubimac.getKorisnikId()==korisnik.getId())
                filtriraniLjubimci.add(ljubimac);

        }

        return filtriraniLjubimci;
    }
}
