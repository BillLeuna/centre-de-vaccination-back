package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Authentification;
import com.example.CentreDeVaccination.Repositories.AuthentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthentificationService {

    public AuthentificationRepository authentificationRepository;

    @Autowired
    public AuthentificationService(AuthentificationRepository authentificationRepository) {
        this.authentificationRepository = authentificationRepository;
    }

    public AuthentificationService() {

    }

    public Authentification saveAuthentification(Authentification authentification) {
        return authentificationRepository.save(authentification);
    }

    public Authentification findOneById(Long id) {
        return authentificationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Authentification not found with id: " + id));
    }

    public List<Authentification> findAll() {
        return authentificationRepository.findAll();
    }

    public Authentification update(Long id, Authentification updatedAuthentification) {
        return authentificationRepository.findById(id)
                .map(authentification -> {
                    authentification.setEmail(updatedAuthentification.getEmail());
                    authentification.setMdp(updatedAuthentification.getMdp());

                    return authentificationRepository.save(authentification);
                })
                .orElseThrow(() -> new RuntimeException("Authentification not found!"));
    }

    public void delete(long id) {
        Authentification authentificationToDelete = authentificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Authentification not found!"));

        authentificationRepository.delete(authentificationToDelete);
    }
}
