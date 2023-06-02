package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.ForbiddenException;
import com.example.PetWalkback.models.dto.JwtUser;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.requests.ChangeRoleRequest;
import com.example.PetWalkback.models.requests.ChangeStatusRequest;
import com.example.PetWalkback.models.requests.PasswordUpdateRequest;
import com.example.PetWalkback.models.requests.UserUpdateRequest;
import com.example.PetWalkback.services.KorisnikService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {
    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<Korisnik> findAll() {
        return korisnikService.findAll(Korisnik.class);
    }
    @PutMapping("/{id}")//novo
    public Korisnik update(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        return korisnikService.update(id, request);
    }

    @PatchMapping("/{id}/status")//novo
    public void changeStatus(@PathVariable Integer id, @RequestBody @Valid ChangeStatusRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeStatus(id, request);
    }

    @PatchMapping("/{id}/role")//novo
    public void changeRole(@PathVariable Integer id, @RequestBody @Valid ChangeRoleRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if(!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeRole(id, request);
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        korisnikService.saveImage(file.getBytes(), file.getOriginalFilename());
    }
    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]>  downloadImage(@PathVariable String imageName) throws IOException {
        return korisnikService.getImage(imageName);
    }

    @PutMapping("/{id}/{username}")
    public Korisnik updatePassword(@PathVariable Integer id, @Valid @RequestBody PasswordUpdateRequest request, @PathVariable String username, Authentication auth){
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        return korisnikService.updatePassword(id, request, username);
    }
}
