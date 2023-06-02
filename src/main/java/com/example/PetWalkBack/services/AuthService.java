package com.example.PetWalkback.services;

import com.example.PetWalkback.models.dto.LoginResponse;
import com.example.PetWalkback.models.requests.LoginRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
