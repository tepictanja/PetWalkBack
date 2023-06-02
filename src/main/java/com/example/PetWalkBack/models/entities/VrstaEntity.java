package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "vrsta")
public class VrstaEntity implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Naziv", nullable = false, length = 45)
    private String naziv;
    @OneToMany(mappedBy = "vrsta")
    private List<LjubimacEntity> ljubimacs;
    @OneToMany(mappedBy = "vrsta")
    private List<PonudaEntity> ponudas;
}
