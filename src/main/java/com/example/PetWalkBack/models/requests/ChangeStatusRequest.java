package com.example.PetWalkback.models.requests;

import com.example.PetWalkback.models.enums.UserStatus;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusRequest {
    @NotNull
    private UserStatus status;
}
