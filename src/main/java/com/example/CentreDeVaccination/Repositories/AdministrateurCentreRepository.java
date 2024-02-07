package com.example.CentreDeVaccination.Repositories;

import com.example.CentreDeVaccination.Models.AdministrateurCentre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrateurCentreRepository extends JpaRepository<AdministrateurCentre, Long> {

    Optional<AdministrateurCentre> findByEmail(String email);
}
