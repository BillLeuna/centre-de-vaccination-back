package com.example.CentreDeVaccination.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "Authentification")
@NoArgsConstructor
@AllArgsConstructor
public class Authentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authentification")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "mdp")
    private String mdp;
}
