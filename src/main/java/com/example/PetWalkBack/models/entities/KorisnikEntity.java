package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import com.example.PetWalkback.models.enums.Role;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "korisnik")
public class KorisnikEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Ime", nullable = false, length = 45)
    private String firstName; //ime;
    @Basic
    @Column(name = "Prezime", nullable = false, length = 45)
    private String lastName; //prezime;
    @Basic
    @Column(name = "Korisnicko_ime", nullable = false, length = 45)
    private String username; //korisnickoIme;
    @Basic
    @Column(name = "Lozinka", nullable = false, length = 100)
    private String password; //lozinka;
    @Basic
    @Column(name = "Email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "Broj_telefona", nullable = false, length = 45)
    private String phoneNumber;
    @Basic
    @Column(name = "photo", length = 225)
    private String photo;
    @Basic
    @Column(name = "opis", length = 225)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private Role role;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;
    public enum Status {
        REQUESTED, ACTIVE, BLOCKED
    }
}
