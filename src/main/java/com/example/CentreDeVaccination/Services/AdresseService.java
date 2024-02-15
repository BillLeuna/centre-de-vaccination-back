package com.example.CentreDeVaccination.Services;

import java.util.List;

import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Repositories.CentreRepository;
import com.example.CentreDeVaccination.Repositories.MedecinRepository;
import com.example.CentreDeVaccination.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Adresse;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Repositories.AdresseRepository;

@Service
public class AdresseService {

    public AdresseRepository adresseRepository;
    public MedecinRepository medecinRepository;
    public PatientRepository patientRepository;
    public CentreRepository centreRepository;

    @Autowired
    public AdresseService(AdresseRepository adresseRepository,
                          MedecinRepository medecinRepository,
                          PatientRepository patientRepository,
                          CentreRepository centreRepository) {
        this.adresseRepository = adresseRepository;
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.centreRepository = centreRepository;
    }

    public AdresseService() {

    }

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public Adresse findOneById(Long id) {
        return adresseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Adresse not found with id: " + id));
    }

    public List<Adresse> findAll() {
        return adresseRepository.findAll();
    }

    public Adresse update(Long id, Adresse updatedAdresse) {
        return adresseRepository.findById(id)
                .map(adresse -> {
                    adresse.setVille(updatedAdresse.getVille());
                    adresse.setRue(updatedAdresse.getRue());
                    adresse.setZip_code(updatedAdresse.getZip_code());
                    adresse.setPatient(updatedAdresse.getPatient());
                    adresse.setCentre(updatedAdresse.getCentre());
                    adresse.setMedecins(updatedAdresse.getMedecins());

                    if (updatedAdresse.getMedecins() != null) {
                        for (Medecin medecin : updatedAdresse.getMedecins()) {
                            medecinRepository.save(medecin);
                        }
                    }

                    if (updatedAdresse.getPatient() != null) {
                        patientRepository.save(updatedAdresse.getPatient());
                    }

                    if (updatedAdresse.getCentre() != null) {
                        centreRepository.save(updatedAdresse.getCentre());
                    }

                    return adresseRepository.save(adresse);
                })
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée !"));
    }

    public void delete(Long id) {
        Adresse adresseToDelete = adresseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée !"));

        if (adresseToDelete.getMedecins() != null) {
            for (Medecin medecin : adresseToDelete.getMedecins()) {
                medecin.getCentre().setAdresse(null);
                medecinRepository.save(medecin);
            }
        }

        if (adresseToDelete.getPatient() != null) {
            adresseToDelete.getPatient().setAdresse(null);
            patientRepository.save(adresseToDelete.getPatient());
        }

        if (adresseToDelete.getCentre() != null) {
            adresseToDelete.getCentre().setAdresse(null);
            centreRepository.save(adresseToDelete.getCentre());
        }

        adresseRepository.delete(adresseToDelete);
    }

}
