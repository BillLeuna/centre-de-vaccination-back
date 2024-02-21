package com.example.CentreDeVaccination.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(exclude = { "medecins", "adresse", "centres" })
@Table(name = "Patients")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "dateDeNaissance")
    private Date dateDeNaissance;

    @Column(name = "dateDInscription")
    private Date dateDInscription;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "medecins-patient")
    private List<Medecin> medecins = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "centres-patient")
    private List<Centre> centres = new ArrayList<>();

}
