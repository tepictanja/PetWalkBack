package com.example.PetWalkback.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class UserUpdateRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @Nullable
    private String photo;
    @Nullable
    private String description;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
}
