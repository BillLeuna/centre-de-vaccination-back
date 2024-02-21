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
@EqualsAndHashCode(exclude = { "adresse", "medecins", "patients" })
@Table(name = "Centre")
@NoArgsConstructor
@AllArgsConstructor
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "medecins-centre")
    private List<Medecin> medecins = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "patients-centre")
    private List<Patient> patients = new ArrayList<>();

}
