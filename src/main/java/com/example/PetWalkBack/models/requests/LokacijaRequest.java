package com.example.PetWalkback.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LokacijaRequest {

    @NotNull
    private Integer mjestoId;
    @NotNull
    private Integer korisnikId;
}
