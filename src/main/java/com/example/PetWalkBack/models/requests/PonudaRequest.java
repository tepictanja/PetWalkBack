package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PonudaRequest {

    @NotNull
    private Integer ponudaId;
    @NotNull
    private Integer vrstaId;
    @NotNull
    private Integer uslugaId;
}
