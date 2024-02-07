package com.example.CentreDeVaccination.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CentreDeVaccination.Models.Docteur;

import java.util.Optional;

public interface DocteurRepository extends JpaRepository<Docteur, Long> {

    Optional<Docteur> findByEmail(String email);
}
