package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.entities.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemEntityRepository extends JpaRepository<ProblemEntity, Integer> {
}
