package com.example.PetWalkback.services;

import com.example.PetWalkback.base.CrudService;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.requests.*;
import org.springframework.http.ResponseEntity;

public interface KorisnikService extends CrudService<Integer>{
    void signUp(SignUpRequest request);
    void changeStatus(Integer userId, ChangeStatusRequest request);
    void changeRole(Integer userId, ChangeRoleRequest request);
    Korisnik update(Integer id, UserUpdateRequest user);
    void saveImage(byte[] data, String name);
    ResponseEntity<byte[]> getImage(String imageName);
    public Korisnik updatePassword(Integer id, PasswordUpdateRequest request, String usrename);
}
