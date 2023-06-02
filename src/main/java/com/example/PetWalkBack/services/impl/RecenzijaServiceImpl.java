package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.RecenzijaEntity;
import com.example.PetWalkback.repositories.RecenzijaEntityRepository;
import com.example.PetWalkback.services.RecenzijaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RecenzijaServiceImpl extends CrudJpaService<RecenzijaEntity, Integer> implements RecenzijaService {

    private final RecenzijaEntityRepository repository;
    public RecenzijaServiceImpl(ModelMapper modelMapper, RecenzijaEntityRepository repository) {
        super(repository, modelMapper, RecenzijaEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }
}
