package com.example.PetWalkback.base;

import com.example.PetWalkback.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;

public interface CrudService<ID extends Serializable> {
    <T> List<T> findAll(Class<T> resultDtoClass);

    <T> T findById(ID id, Class<T> resultDtoClass) throws NotFoundException; //bez throws

    <T, U> T insert(U object, Class<T> resultDtoClass);

    <T, U> T update(ID id, U object, Class<T> resultDtoClass);

    void delete(ID id);//bez throws
}
