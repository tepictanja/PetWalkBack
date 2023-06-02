package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.models.dto.JwtUser;
import com.example.PetWalkback.repositories.KorisnikEntityRepository;
import com.example.PetWalkback.services.JwtUserDetailsService;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private final KorisnikEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;

    public JwtUserDetailsServiceImpl(KorisnikEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {

        return modelMapper.map(userEntityRepository.findByUsernameAndStatus(username, KorisnikEntity.Status.ACTIVE).
                orElseThrow(() -> new UsernameNotFoundException(username)), JwtUser.class);
    }
}
