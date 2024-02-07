package com.example.CentreDeVaccination.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Docteur;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Repositories.DocteurRepository;

@Service
public class DocteurService {

    public DocteurRepository docteurRepository;

    @Autowired
    public DocteurService(DocteurRepository docteurRepository) {
        this.docteurRepository = docteurRepository;
    }

    public DocteurService() {

    }

    public Docteur saveDocteur(Docteur docteur) {
        return docteurRepository.save(docteur);
    }

    public Docteur findOneById(Long id) {
        return docteurRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Docteur not found with id: " + id));
    }

    public List<Docteur> findAll() {
        return docteurRepository.findAll();
    }

    public Docteur update(Long id, Docteur updatedDocteur) {
        return docteurRepository.findById(id)
                .map(docteur -> {
                    docteur.setPrenom(updatedDocteur.getPrenom());
                    docteur.setNom(updatedDocteur.getNom());
                    docteur.setTelephone(updatedDocteur.getTelephone());
                    docteur.setEmail(updatedDocteur.getEmail());
                    docteur.setAdresse(updatedDocteur.getAdresse());
                    docteur.setPatients(updatedDocteur.getPatients());

                    return docteurRepository.save(docteur);
                })
                .orElseThrow(() -> new RuntimeException("Docteur not found!"));
    }

    public void delete(long id) {
        Docteur docteurToDelete = docteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docteur not found!"));

        // Dissocier le docteur des entités associées (Adresse, Patient, DocteurPatient)
        // si nécessaire
        if (docteurToDelete.getAdresse() != null) {
            docteurToDelete.getAdresse().setDocteur(null);
        }

        for (Patient patient : docteurToDelete.getPatients()) {
            patient.setDocteur(null);
        }

        docteurRepository.delete(docteurToDelete);
    }

    public Docteur findByEmail(String email) {
        return docteurRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Docteur not found with email: " + email));
    }
}
