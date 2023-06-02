package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.IzvjestajEntity;
import com.example.PetWalkback.repositories.IzvjestajEntityRepository;
import com.example.PetWalkback.repositories.IzvjestajEntityRepository;
import com.example.PetWalkback.services.IzvjestajService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IzvjestajServiceImpl extends CrudJpaService<IzvjestajEntity, Integer> implements IzvjestajService {
    private final IzvjestajEntityRepository repository;
    public IzvjestajServiceImpl(ModelMapper modelMapper, IzvjestajEntityRepository repository) {
        super(repository, modelMapper, IzvjestajEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }
}
