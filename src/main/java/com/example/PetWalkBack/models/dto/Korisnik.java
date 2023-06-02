package com.example.PetWalkback.models.dto;

import com.example.PetWalkback.models.enums.Role;
import com.example.PetWalkback.models.enums.UserStatus;
import lombok.Data;

@Data
public class Korisnik {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String photo;
    private String description;
    private Role role;
    private UserStatus status;
}
