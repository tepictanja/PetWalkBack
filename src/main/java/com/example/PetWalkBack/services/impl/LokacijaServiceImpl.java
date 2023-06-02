package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.LokacijaEntity;
import com.example.PetWalkback.repositories.LokacijaEntityRepository;
import com.example.PetWalkback.services.LokacijaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LokacijaServiceImpl extends CrudJpaService<LokacijaEntity, Integer> implements LokacijaService {
    private final LokacijaEntityRepository repository;
    public LokacijaServiceImpl(ModelMapper modelMapper, LokacijaEntityRepository repository) {
        super(repository, modelMapper, LokacijaEntity.class);
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
