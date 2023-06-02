package com.example.PetWalkback.services;

import com.example.PetWalkback.base.CrudService;
import org.springframework.http.ResponseEntity;

public interface LjubimacService extends CrudService<Integer> {
    void saveImage(byte[] data, String name);
    ResponseEntity<byte[]> getImage(String imageName);
}

