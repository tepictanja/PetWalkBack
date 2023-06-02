package com.example.PetWalkback.config;

import com.example.PetWalkback.models.dto.JwtUser;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import com.example.PetWalkback.services.KorisnikService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<KorisnikEntity> {

    private final KorisnikService userService;

    public AuditorAwareImpl(KorisnikService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<KorisnikEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtUser) {
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            return Optional.of(userService.findById(jwtUser.getId(), KorisnikEntity.class));
        }
        return Optional.empty();
    }
}
