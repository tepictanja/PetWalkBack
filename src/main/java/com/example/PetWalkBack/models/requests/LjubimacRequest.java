package com.example.PetWalkback.models.requests;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class LjubimacRequest {
    @NotBlank
    private String ime;
    @NotBlank
    private String opis;
    @Nullable
    private String slika;
    @NotNull
    private  Integer korisnikId;
    @NotNull
    private Integer vrstaId;
}
