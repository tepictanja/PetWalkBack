package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.entities.UslugaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UslugaEntityRepository extends JpaRepository<UslugaEntity, Integer> {
    Boolean existsByNaziv(String naziv);

    Boolean existsByNazivAndIdNot(String naziv, Integer id);
}
