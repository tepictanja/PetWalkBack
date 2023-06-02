package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lokacija")
public class LokacijaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    /*@Basic
    @Column(name = "Koordinate", nullable = false, length = 25)
    private String koordinate;*/
    @ManyToOne
    @JoinColumn(name = "mjesto_id", referencedColumnName = "id", nullable = false)
    private MjestoEntity mjesto;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;

}
