package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class UslugaRequest {

    @NotBlank
    private String naziv;
    @NotNull
    private Integer uslugaId;
}
