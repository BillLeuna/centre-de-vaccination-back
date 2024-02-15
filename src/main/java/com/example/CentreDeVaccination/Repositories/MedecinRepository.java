package com.example.CentreDeVaccination.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CentreDeVaccination.Models.Medecin;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Optional<Medecin> findByEmail(String email);
}
