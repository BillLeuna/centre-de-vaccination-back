package com.example.CentreDeVaccination.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(exclude = { "centre", "medecins", "patient" })
@Table(name = "Adresse")
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long id;

    @Column(name = "ville")
    private String ville;

    @Column(name = "rue")
    private String rue;

    @Column(name = "zip_code")
    private Long zipCode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Medecin> medecins = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    @JsonBackReference(value = "adresse-patient")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_centre")
    @JsonBackReference(value = "adresse-centre")
    private Centre centre;

}
