package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.MjestoEntity;
import com.example.PetWalkback.repositories.MjestoEntityRepository;
import com.example.PetWalkback.services.MjestoService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MjestoServiceImpl extends CrudJpaService<MjestoEntity, Integer> implements MjestoService {
    public MjestoServiceImpl(ModelMapper modelMapper, MjestoEntityRepository repository) {
        super(repository, modelMapper, MjestoEntity.class);
    }

}
