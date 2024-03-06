package com.example.CentreDeVaccination.Services;

import java.util.List;

<<<<<<< Updated upstream
=======
import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Repositories.*;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Docteur;
import com.example.CentreDeVaccination.Models.Patient;

@Service
public class PatientService {

<<<<<<< Updated upstream
    public PatientRepository patientRepository;
    public DocteurService docteurService;

    @Autowired
    public PatientService(PatientRepository patientRepository, DocteurService docteurService) {
        this.patientRepository = patientRepository;
        this.docteurService = docteurService;
=======
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private AdresseRepository adresseRepository;
    private CentreRepository centreRepository;
    private AuthentificationRepository authentificationRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          MedecinRepository medecinRepository,
                          AdresseRepository adresseRepository,
                          CentreRepository centreRepository,
                          AuthentificationRepository authentificationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.adresseRepository = adresseRepository;
        this.centreRepository = centreRepository;
        this.authentificationRepository = authentificationRepository;
>>>>>>> Stashed changes
    }

    public PatientService() {

    }

    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public Patient findOneById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Patient not found with id: " + id));
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Long id, Patient patient) {
        return patientRepository.findById(id)
                .map(p -> {
                    p.setPrenom(patient.getPrenom());
                    p.setNom(patient.getNom());
                    p.setDateDeNaissance(patient.getDateDeNaissance());
                    p.setEmail(patient.getEmail());
                    p.setEmail(patient.getEmail());
                    p.setAdresse(patient.getAdresse());
                    p.setDocteur(patient.getDocteur());

                    updateDocteurList(patient, p);

                    return patientRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Patient non trouvé !"));
    }

    private void updateDocteurList(Patient patient, Patient p) {
        if (patient.getDocteur() != null) {
            List<Patient> patientsList = p.getDocteur().getPatients();
            patientsList.add(patient);

            Docteur docteur = p.getDocteur();
            docteur.setPatients(patientsList);

            docteurService.update(p.getDocteur().getId().longValue(), docteur);
        }

    }

    public void delete(Long id) {
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé !"));

        patientRepository.delete(patientToDelete);
        patientRepository.deleteById(id);
    }

    public Docteur getDocteur(Long id) {
        return patientRepository.findById(id).get().getDocteur();
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Patient not found with email: " + email));
    }
}
