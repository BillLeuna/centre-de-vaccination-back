package com.example.CentreDeVaccination.Repositories;

import com.example.CentreDeVaccination.Models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

}
