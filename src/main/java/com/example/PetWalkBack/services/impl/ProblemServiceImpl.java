package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.ProblemEntity;
import com.example.PetWalkback.repositories.ProblemEntityRepository;
import com.example.PetWalkback.services.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProblemServiceImpl extends CrudJpaService<ProblemEntity, Integer> implements ProblemService {
    private final ProblemEntityRepository repository;
    public ProblemServiceImpl(ModelMapper modelMapper, ProblemEntityRepository repository) {
        super(repository, modelMapper, ProblemEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }
}
