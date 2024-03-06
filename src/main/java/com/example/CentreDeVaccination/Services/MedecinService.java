package com.example.CentreDeVaccination.Services;

import java.util.List;

import com.example.CentreDeVaccination.Models.Authentification;
import com.example.CentreDeVaccination.Repositories.CentreRepository;
import com.example.CentreDeVaccination.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Repositories.MedecinRepository;

@Service
public class MedecinService {

    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private CentreRepository centreRepository;
    private AuthentificationService authentificationService;

    @Autowired
    public MedecinService(MedecinRepository medecinRepository,
                          PatientRepository patientRepository,
                          CentreRepository centreRepository,
                          AuthentificationService authentificationService) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.centreRepository = centreRepository;
        this.authentificationService = authentificationService;
    }

    public MedecinService() {

    }

    public Medecin saveMedecin(Medecin medecin) {
        if (!(authentificationService.doesAuthentificationExists(medecin.getEmail()))) {
            Authentification auth = new Authentification();
            auth.setEmail(medecin.getEmail());
            auth.setMotDePasse("to change");
            auth.setRoleUtilisateur("medecin");
            authentificationService.saveAuthentification(auth);
        }
        return medecinRepository.save(medecin);
    }

    public Medecin findOneById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Medecin not found with id: " + id));
    }

    public List<Medecin> findAll() {
        return medecinRepository.findAll();
    }

    public Medecin update(Medecin updatedMedecin) {
        return medecinRepository.findById(updatedMedecin.getId())
                .map(medecin -> {
                    medecin.setPrenom(updatedMedecin.getPrenom());
                    medecin.setNom(updatedMedecin.getNom());
                    medecin.setTelephone(updatedMedecin.getTelephone());
                    medecin.setEmail(updatedMedecin.getEmail());
                    medecin.setPatients(updatedMedecin.getPatients());
                    medecin.setCentre(updatedMedecin.getCentre());

                    if (updatedMedecin.getPatients() != null) {
                        patientRepository.saveAll(updatedMedecin.getPatients());
                    }

                    if (updatedMedecin.getCentre() != null) {
                        centreRepository.save(updatedMedecin.getCentre());
                    }
                    return medecinRepository.save(medecin);
                })
                .orElseThrow(() -> new RuntimeException("Medecin not found!"));
    }

    public void delete(long id) {
        Medecin medecinToDelete = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found!"));

        if (medecinToDelete.getPatients() != null) {
            for (Patient patient : medecinToDelete.getPatients()) {
                patient.getMedecins().remove(medecinToDelete);
                patientRepository.save(patient);
            }
        }

        if (medecinToDelete.getCentre() != null) {
            medecinToDelete.getCentre().getMedecins().remove(medecinToDelete);
            centreRepository.save(medecinToDelete.getCentre());
        }

        // Supprimer le médecin
        medecinRepository.delete(medecinToDelete);
    }


    public Medecin findByEmail(String email) {
        return medecinRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Medecin not found with email: " + email));
    }
}
