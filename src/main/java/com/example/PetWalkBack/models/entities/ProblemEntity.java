package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "problem")
public class ProblemEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Sadrzaj", nullable = false, length = 45)
    private String sadrzaj;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
}
