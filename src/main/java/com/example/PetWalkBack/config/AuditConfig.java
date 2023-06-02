package com.example.PetWalkback.config;

import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditConfig {
    private final KorisnikService userService;

    public AuditConfig(KorisnikService userService) {
        this.userService = userService;
    }

    @Bean
    AuditorAware<KorisnikEntity> auditorProvider() {
        return new AuditorAwareImpl(userService);
    }
}
