package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.entities.LjubimacEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LjubimacEntityRepository extends JpaRepository<LjubimacEntity, Integer>  {
    Boolean existsByImeAndIdNot(String name, Integer id);
}
