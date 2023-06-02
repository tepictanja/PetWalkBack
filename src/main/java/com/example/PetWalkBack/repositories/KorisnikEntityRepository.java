package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.entities.KorisnikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KorisnikEntityRepository extends JpaRepository<KorisnikEntity, Integer> {
    Optional<KorisnikEntity> findByUsernameAndStatus(String username, KorisnikEntity.Status status);
    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndIdNot(String username, Integer id);
    Boolean existsByIdNot(Integer id);
}
