package com.example.CentreDeVaccination.Controllers;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.SuperAdmin;
import com.example.CentreDeVaccination.Services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superAdmins")
@CrossOrigin(origins = "http://localhost:4200")
public class SuperAdminRestController {

    private final SuperAdminService superAdminService;

    @Autowired
    public SuperAdminRestController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<SuperAdmin>> getAllSuperAdmins(@RequestParam(required = false) String nom) {
        if (nom != null && !nom.isEmpty()) {
            // Filtrer la liste des superadmins par nom qui commence par la valeur du paramètre
            // 'nom'
            List<SuperAdmin> filteredSuperAdmins = superAdminService.findAll()
                    .stream()
                    .filter(superAdmin -> superAdmin.getNom().startsWith(nom))
                    .toList();

            return new ResponseEntity<>(filteredSuperAdmins, HttpStatus.OK);
        } else {
            // Si aucun paramètre 'nom' n'est fourni, renvoyer la liste complète des
            // superadmins
            List<SuperAdmin> allSuperAdmins = superAdminService.findAll();
            return new ResponseEntity<>(allSuperAdmins, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/get/{id}")
    public SuperAdmin getSuperAdminById(@PathVariable Long id) {
        return superAdminService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<SuperAdmin> createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin savedSuperAdmin = superAdminService.saveSuperAdmin(superAdmin);
        return new ResponseEntity<>(savedSuperAdmin, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public SuperAdmin updateSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        return superAdminService.update(superAdmin.getId(), superAdmin);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteSuperAdmin(@PathVariable Long id) {
        superAdminService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("SuperAdmin not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<SuperAdmin> getSuperAdminByEmail(@PathVariable String email) {
        SuperAdmin superAdmin = superAdminService.findByEmail(email);
        return new ResponseEntity<>(superAdmin, HttpStatus.OK);
    }
}
