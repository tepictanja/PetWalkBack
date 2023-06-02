package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.VrstaEntity;
import com.example.PetWalkback.repositories.VrstaEntityRepository;
import com.example.PetWalkback.services.VrstaService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VrstaServiceImpl extends CrudJpaService<VrstaEntity, Integer> implements VrstaService {

    public VrstaServiceImpl(ModelMapper modelMapper, VrstaEntityRepository repository) {
        super(repository, modelMapper, VrstaEntity.class);
    }
}
