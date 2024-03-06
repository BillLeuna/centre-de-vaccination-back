package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Authentification;
import com.example.CentreDeVaccination.Models.SuperAdmin;
import com.example.CentreDeVaccination.Repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {

    private SuperAdminRepository superAdminRepository;
    private AuthentificationService authentificationService;

    @Autowired
    public SuperAdminService(SuperAdminRepository superAdminRepository,
                             AuthentificationService authentificationService) {
        this.superAdminRepository = superAdminRepository;
        this.authentificationService = authentificationService;
    }

    public SuperAdminService() {

    }

    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        if (!(authentificationService.doesAuthentificationExists(superAdmin.getEmail()))) {
            Authentification auth = new Authentification();
            auth.setEmail(superAdmin.getEmail());
            auth.setMotDePasse("to change");
            auth.setRoleUtilisateur("superAdmin");
            authentificationService.saveAuthentification(auth);
        }
        return superAdminRepository.save(superAdmin);
    }

    public SuperAdmin findOneById(Long id) {
        return superAdminRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SuperAdmin not found with id: " + id));
    }

    public List<SuperAdmin> findAll() {
        return superAdminRepository.findAll();
    }

    public SuperAdmin update(SuperAdmin updatedSuperAdmin) {
        return superAdminRepository.findById(updatedSuperAdmin.getId())
                .map(superAdmin -> {
                    superAdmin.setPrenom(updatedSuperAdmin.getPrenom());
                    superAdmin.setNom(updatedSuperAdmin.getNom());
                    superAdmin.setEmail(updatedSuperAdmin.getEmail());

                    return superAdminRepository.save(superAdmin);
                })
                .orElseThrow(() -> new RuntimeException("SuperAdmin not found!"));
    }

    public void delete(long id) {
        SuperAdmin superAdminToDelete = superAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SuperAdmin not found!"));

        superAdminRepository.delete(superAdminToDelete);
    }

    public SuperAdmin findByEmail(String email) {
        return superAdminRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("SuperAdmin not found with email: " + email));
    }
}
