package com.example.CentreDeVaccination.Services;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.SuperAdmin;
import com.example.CentreDeVaccination.Repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {

    private SuperAdminRepository superAdminRepository;

    @Autowired
    public SuperAdminService(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    public SuperAdminService() {

    }

    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
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
