package com.example.CentreDeVaccination.Services;

import java.util.List;

import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Repositories.AdresseRepository;
import com.example.CentreDeVaccination.Repositories.CentreRepository;
import com.example.CentreDeVaccination.Repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Repositories.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private AdresseRepository adresseRepository;
    private CentreRepository centreRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          MedecinRepository medecinRepository,
                          AdresseRepository adresseRepository,
                          CentreRepository centreRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.adresseRepository = adresseRepository;
        this.centreRepository = centreRepository;
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

    public Patient update(Long id, Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setPrenom(updatedPatient.getPrenom());
                    patient.setNom(updatedPatient.getNom());
                    patient.setDateDeNaissance(updatedPatient.getDateDeNaissance());
                    patient.setEmail(updatedPatient.getEmail());
                    patient.setEmail(updatedPatient.getEmail());
                    patient.setAdresse(updatedPatient.getAdresse());
                    patient.setMedecins(updatedPatient.getMedecins());
                    patient.setDateDInscription(updatedPatient.getDateDInscription());
                    patient.setTelephone(updatedPatient.getTelephone());

                    if (updatedPatient.getAdresse() != null) {
                        adresseRepository.save(updatedPatient.getAdresse());
                    }

                    if (updatedPatient.getMedecins() != null) {
                        for (Medecin medecin : updatedPatient.getMedecins()) {
                            medecinRepository.save(medecin);
                        }
                    }

                    if (updatedPatient.getCentres() != null) {
                        for (Centre centre : updatedPatient.getCentres()) {
                            centreRepository.save(centre);
                        }
                    }

                    return patientRepository.save(updatedPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient non trouvé !"));
    }

    public void delete(Long id) {
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé !"));

        if (patientToDelete.getAdresse() != null) {
            patientToDelete.getAdresse().setPatient(null);
            adresseRepository.save(patientToDelete.getAdresse());
        }

        if (patientToDelete.getMedecins() != null) {
            for (Medecin medecin : patientToDelete.getMedecins()) {
                medecin.getPatients().remove(patientToDelete);
                medecinRepository.save(medecin);
            }
        }

        if (patientToDelete.getCentres() != null) {
            for (Centre centre : patientToDelete.getCentres()) {
                centre.getPatients().remove(patientToDelete);
                centreRepository.save(centre);
            }
        }

        patientRepository.delete(patientToDelete);
    }

    public List<Medecin> getMedecins(Long id) {
        return patientRepository.findById(id).get().getMedecins();
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Patient not found with email: " + email));
    }
}
