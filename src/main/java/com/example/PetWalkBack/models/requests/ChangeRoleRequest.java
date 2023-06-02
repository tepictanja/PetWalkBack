package com.example.PetWalkback.models.requests;

import com.example.PetWalkback.models.enums.Role;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeRoleRequest {
    @NotNull
    private Role role;
}
