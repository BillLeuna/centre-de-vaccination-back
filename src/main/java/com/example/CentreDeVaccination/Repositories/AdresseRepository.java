package com.example.CentreDeVaccination.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CentreDeVaccination.Models.Adresse;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    Adresse findByVilleAndRueAndZipCode(String ville, String rue, Integer zipCode);

}
