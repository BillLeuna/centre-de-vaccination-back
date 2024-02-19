package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.AdministrateurCentre;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Repositories.AdministrateurCentreRepository;
import com.example.CentreDeVaccination.Repositories.CentreRepository;
import com.example.CentreDeVaccination.Repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurCentreService {

    private final AdministrateurCentreRepository administrateurCentreRepository;
    private final CentreRepository centreRepository;
    private final MedecinRepository medecinRepository;

    @Autowired
    public AdministrateurCentreService(AdministrateurCentreRepository administrateurCentreRepository,
                                       CentreRepository centreRepository,
                                       MedecinRepository medecinRepository) {
        this.administrateurCentreRepository = administrateurCentreRepository;
        this.centreRepository = centreRepository;
        this.medecinRepository = medecinRepository;

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

    public AdministrateurCentre update(AdministrateurCentre updatedAdministrateurCentre) {
        return administrateurCentreRepository.findById(updatedAdministrateurCentre.getId())
                .map(administrateurCentre -> {
                    administrateurCentre.setPrenom(updatedAdministrateurCentre.getPrenom());
                    administrateurCentre.setNom(updatedAdministrateurCentre.getNom());
                    administrateurCentre.setEmail(updatedAdministrateurCentre.getEmail());
                    administrateurCentre.setCentre(updatedAdministrateurCentre.getCentre());

                    if(updatedAdministrateurCentre.getCentre() != null) {
                        centreRepository.save(updatedAdministrateurCentre.getCentre());
                    }

                    return administrateurCentreRepository.save(administrateurCentre);
                })
                .orElseThrow(() -> new RuntimeException("AdministrateurCentre not found!"));
    }

    public void delete(long id) {
        AdministrateurCentre administrateurCentreToDelete = administrateurCentreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdministrateurCentre not found!"));

        administrateurCentreRepository.delete(administrateurCentreToDelete);
    }

    public AdministrateurCentre findByEmail(String email) {
        return administrateurCentreRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("AdministrateurCentre not found with email: " + email));
    }
}
