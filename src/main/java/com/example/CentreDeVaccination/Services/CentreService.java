package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Repositories.AdresseRepository;
import com.example.CentreDeVaccination.Repositories.MedecinRepository;
import com.example.CentreDeVaccination.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Repositories.CentreRepository;

import java.util.List;

@Service
public class CentreService {

    private final CentreRepository centreRepository;
    private final AdresseRepository adresseRepository;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CentreService(CentreRepository centreRepository,
                         AdresseRepository adresseRepository,
                         MedecinRepository medecinRepository,
                         PatientRepository patientRepository) {
        this.centreRepository = centreRepository;
        this.adresseRepository = adresseRepository;
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
    }

    public Centre saveCentre(Centre centre) {
        return centreRepository.save(centre);
    }

    public Centre findOneById(Long id) {
        return centreRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Centre not found with id: " + id));
    }

    public List<Centre> findAll() {
        return centreRepository.findAll();
    }

    public Centre update(Centre updatedCentre) {
        return centreRepository.findById(updatedCentre.getId())
                .map(centre -> {
                    centre.setNom(updatedCentre.getNom());
                    centre.setAdresse(updatedCentre.getAdresse());
                    centre.setMedecins(updatedCentre.getMedecins());
                    centre.setPatients(updatedCentre.getPatients());

                    if (updatedCentre.getAdresse() != null) {
                        adresseRepository.save(updatedCentre.getAdresse());
                    }

                    if (updatedCentre.getMedecins() != null) {
                        medecinRepository.saveAll(updatedCentre.getMedecins());
                    }

                    if (updatedCentre.getPatients() != null) {
                        patientRepository.saveAll(updatedCentre.getPatients());
                    }

                    return centreRepository.save(centre);
                })
                .orElseThrow(() -> new RuntimeException("Centre not found!"));
    }

    public void delete(long id) {
        Centre centreToDelete = centreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centre not found!"));

        if (centreToDelete.getAdresse() != null) {
            centreToDelete.getAdresse().setCentre(null);
            adresseRepository.save(centreToDelete.getAdresse());
        }

        if (centreToDelete.getMedecins() != null) {
            for (Medecin medecin : centreToDelete.getMedecins()) {
                medecin.setCentre(null);
                medecinRepository.save(medecin);
            }
        }

        if (centreToDelete.getPatients() != null) {
            for (Patient patient : centreToDelete.getPatients()) {
                patient.getCentres().remove(centreToDelete);
                patientRepository.save(patient);
            }
        }

        centreRepository.delete(centreToDelete);
    }
}
