package com.example.CentreDeVaccination.Repositories;

import com.example.CentreDeVaccination.Models.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {
    Authentification findByEmailAndMotDePasse(String email, String mdp);
}
