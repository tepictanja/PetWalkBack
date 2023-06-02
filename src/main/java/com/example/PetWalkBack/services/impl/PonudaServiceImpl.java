package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.PonudaEntity;
import com.example.PetWalkback.repositories.PonudaEntityRepository;
import com.example.PetWalkback.services.PonudaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PonudaServiceImpl extends CrudJpaService<PonudaEntity, Integer> implements PonudaService {
    private final PonudaEntityRepository repository;
    public PonudaServiceImpl(ModelMapper modelMapper, PonudaEntityRepository repository){
        super(repository, modelMapper, PonudaEntity.class);
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
