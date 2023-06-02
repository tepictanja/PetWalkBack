package com.example.PetWalkback.repositories;


import com.example.PetWalkback.models.entities.CijenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CijenaEntityRepository  extends JpaRepository<CijenaEntity, Integer> {
    Boolean existsByUslugaIdAndKorisnikId(Integer uslugaId, Integer korisnikId);
}
