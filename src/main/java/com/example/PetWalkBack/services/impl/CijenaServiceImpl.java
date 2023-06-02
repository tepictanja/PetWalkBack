package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.CijenaEntity;
import com.example.PetWalkback.repositories.CijenaEntityRepository;
import com.example.PetWalkback.services.CijenaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CijenaServiceImpl extends CrudJpaService<CijenaEntity, Integer> implements CijenaService {

    private final CijenaEntityRepository repository;
    public CijenaServiceImpl(ModelMapper modelMapper, CijenaEntityRepository repository) {
        super(repository, modelMapper, CijenaEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }

    @Override
    public <T, U> T update(Integer integer, U object, Class<T> resultDtoClass) {
        if (!repository.existsById(integer))
            throw new ConflictException();
        return super.update(integer, object, resultDtoClass);
    }
}
