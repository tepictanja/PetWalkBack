package com.example.PetWalkback.controllers;

import com.example.PetWalkback.models.dto.JwtUser;
import com.example.PetWalkback.models.dto.LoginResponse;
import com.example.PetWalkback.models.requests.LoginRequest;
import com.example.PetWalkback.models.requests.SignUpRequest;
import com.example.PetWalkback.services.AuthService;
import com.example.PetWalkback.services.KorisnikService;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService service;
    private final KorisnikService userService;

    public AuthController(AuthService service, KorisnikService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }

    @GetMapping("state")
    public LoginResponse state(Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return userService.findById(jwtUser.getId(), LoginResponse.class);
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }
}
