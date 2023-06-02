package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.UslugaEntity;
import com.example.PetWalkback.repositories.UslugaEntityRepository;
import com.example.PetWalkback.services.UslugaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UslugaServiceImpl extends CrudJpaService<UslugaEntity, Integer> implements UslugaService {
    private final UslugaEntityRepository repository;
    public UslugaServiceImpl(ModelMapper modelMapper, UslugaEntityRepository repository){
        super(repository, modelMapper, UslugaEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        if(repository.existsByNaziv(getModelMapper().map(object, getEntityClass()).getNaziv()))
            throw new ConflictException();
        return super.insert(object, resultDtoClass);
    }

    @Override
    public <T, U> T update(Integer integer, U object, Class<T> resultDtoClass) {
        if (!repository.existsByNazivAndIdNot(getModelMapper().map(object, getEntityClass()).getNaziv(), integer))
            throw new ConflictException();
        return super.update(integer, object, resultDtoClass);
    }
}
