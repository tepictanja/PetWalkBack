package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "mjesto")
public class MjestoEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Naziv", nullable = false, length = 45)
    private String naziv;
    @OneToMany(mappedBy = "mjesto")
    private List<LokacijaEntity> lokacijas;
}
