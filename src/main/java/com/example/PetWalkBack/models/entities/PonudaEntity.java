package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ponuda")
public class PonudaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "vrsta_id", referencedColumnName = "id", nullable = false)
    private VrstaEntity vrsta;
    @ManyToOne
    @JoinColumn(name = "usluga_id", referencedColumnName = "id", nullable = false)
    private UslugaEntity usluga;
}
