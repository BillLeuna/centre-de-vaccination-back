package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.AdministrateurCentre;
import com.example.CentreDeVaccination.Repositories.AdministrateurCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurCentreService {

    private final AdministrateurCentreRepository administrateurCentreRepository;

    @Autowired
    public AdministrateurCentreService(AdministrateurCentreRepository administrateurCentreRepository) {
        this.administrateurCentreRepository = administrateurCentreRepository;
    }

    public AdministrateurCentre saveAdministrateurCentre(AdministrateurCentre administrateurCentre) {
        return administrateurCentreRepository.save(administrateurCentre);
    }

    public AdministrateurCentre findOneById(Long id) {
        return administrateurCentreRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("AdministrateurCentre not found with id: " + id));
    }

    public List<AdministrateurCentre> findAll() {
        return administrateurCentreRepository.findAll();
    }

    public AdministrateurCentre update(Long id, AdministrateurCentre updatedAdministrateurCentre) {
        return administrateurCentreRepository.findById(id)
                .map(administrateurCentre -> {
                    administrateurCentre.setPrenom(updatedAdministrateurCentre.getPrenom());
                    administrateurCentre.setNom(updatedAdministrateurCentre.getNom());
                    administrateurCentre.setEmail(updatedAdministrateurCentre.getEmail());
                    // Ajouter d'autres attributs à mettre à jour selon les besoins

                    return administrateurCentreRepository.save(administrateurCentre);
                })
                .orElseThrow(() -> new RuntimeException("AdministrateurCentre not found!"));
    }

    public void delete(long id) {
        AdministrateurCentre administrateurCentreToDelete = administrateurCentreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdministrateurCentre not found!"));

        // Dissocier administrateurCentre des entités associées si nécessaire
        // (Medecin, Centre, etc.)

        administrateurCentreRepository.delete(administrateurCentreToDelete);
    }

    public AdministrateurCentre findByEmail(String email) {
        return administrateurCentreRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("AdministrateurCentre not found with email: " + email));
    }
}
