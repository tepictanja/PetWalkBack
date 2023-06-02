package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ljubimac")
public class LjubimacEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Ime", nullable = false, length = 45)
    private String ime;
    @Basic
    @Column(name = "Slika", nullable = false, length = 255)
    private String slika;
    @Basic
    @Column(name = "Opis", nullable = true, length = 45)
    private String opis;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "vrsta_id", referencedColumnName = "id", nullable = false)
    private VrstaEntity vrsta;
}
