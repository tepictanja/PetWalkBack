package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;
@Data
public class CijenaRequest {

    @NotNull
    private Integer cijena;
    @NotNull
    private Integer uslugaId;
    @NotNull
    private Integer korisnikId;
}
