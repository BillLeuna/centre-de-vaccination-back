package com.example.CentreDeVaccination.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(exclude = { "adresse", "docteurs" })
@Table(name = "Centre")
@NoArgsConstructor
@AllArgsConstructor
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_adresse")
    @JsonBackReference(value = "adresse-centre")
    private Adresse adresse;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference(value = "docteurs-centre")
    private List<Docteur> docteurs = new ArrayList<>();

}
