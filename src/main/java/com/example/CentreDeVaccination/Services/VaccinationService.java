package com.example.CentreDeVaccination.Services;

import java.util.List;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Vaccination;
import com.example.CentreDeVaccination.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    public Vaccination saveVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    public Vaccination findOneById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Vaccination not found with id: " + id));
    }

    public List<Vaccination> findAll() {
        return vaccinationRepository.findAll();
    }

    public Vaccination update(Long id, Vaccination updatedVaccination) {
        return vaccinationRepository.findById(id)
                .map(vaccination -> {
                    vaccination.setDateReservation(updatedVaccination.getDateReservation());
                    vaccination.setDateVaccination(updatedVaccination.getDateVaccination());
                    vaccination.setStatutDossierPatient(updatedVaccination.getStatutDossierPatient());
                    vaccination.setPatient(updatedVaccination.getPatient());
                    vaccination.setMedecin(updatedVaccination.getMedecin());
                    vaccination.setCentre(updatedVaccination.getCentre());
                    return vaccinationRepository.save(vaccination);
                })
                .orElseThrow(() -> new RuntimeException("Vaccination not found!"));
    }

    public void delete(Long id) {
        Vaccination vaccinationToDelete = vaccinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccination not found!"));

        vaccinationRepository.delete(vaccinationToDelete);
    }
}
