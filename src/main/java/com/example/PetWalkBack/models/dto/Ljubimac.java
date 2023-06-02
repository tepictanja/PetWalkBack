package com.example.PetWalkback.models.dto;

import lombok.Data;

@Data
public class Ljubimac {
    private Integer id;
    private String ime;
    private String slika;
    private String opis;
    private Integer korisnikId;
    private Integer vrstaId;
}
