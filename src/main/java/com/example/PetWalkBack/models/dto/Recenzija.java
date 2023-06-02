package com.example.PetWalkback.models.dto;

import lombok.Data;

@Data
public class Recenzija {
    private Integer id;
    private String komentar;
    private Integer ocjena;
    private Integer korisnikOdId;
    private Integer korisnikZaId;
}
