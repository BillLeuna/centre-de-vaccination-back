package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Models.Docteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Repositories.CentreRepository;

import java.util.List;

@Service
public class CentreService {

    private final CentreRepository centreRepository;

    @Autowired
    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
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

    public Centre update(Long id, Centre updatedCentre) {
        return centreRepository.findById(id)
                .map(centre -> {
                    centre.setNom(updatedCentre.getNom());
                    centre.setAdresse(updatedCentre.getAdresse());
                    centre.setDocteurs(updatedCentre.getDocteurs());

                    return centreRepository.save(centre);
                })
                .orElseThrow(() -> new RuntimeException("Centre not found!"));
    }

    public void delete(long id) {
        Centre centreToDelete = centreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centre not found!"));

        centreRepository.delete(centreToDelete);
    }
}
