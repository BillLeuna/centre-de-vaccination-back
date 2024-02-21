package com.example.CentreDeVaccination.Models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(exclude = { "patient", "medecin", "centre" })
@Table(name = "Vaccinations")
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_superadmin")
    private Long id;

    @Column(name = "date_reservation")
    private Date dateReservation;

    @Column(name = "date_vaccination")
    private Date dateVaccination;

    @Column(name = "statutDossierPatient")
    private StatutDossierPatient statutDossierPatient;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medecin")
    private Medecin medecin;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_centre")
    private Centre centre;


}
