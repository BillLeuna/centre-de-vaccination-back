package com.example.CentreDeVaccination.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Docteur;
import com.example.CentreDeVaccination.Services.DocteurService;

@RestController
@RequestMapping("/docteurs")
@CrossOrigin(origins = "http://localhost:4200")
public class DocteurRestController {

    @Autowired
    private DocteurService docteurService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Docteur>> getAllDocteurs(@RequestParam(required = false) String nom) {
        if (nom != null && !nom.isEmpty()) {
            // Filtrer la liste des docteurs par nom qui commence par la valeur du paramètre
            // 'nom'
            List<Docteur> filteredDocteurs = docteurService.findAll()
                    .stream()
                    .filter(docteur -> docteur.getNom().startsWith(nom))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredDocteurs, HttpStatus.OK);
        } else {
            // Si aucun paramètre 'name' n'est fourni, renvoyer la liste complète des
            // docteurs
            List<Docteur> allDocteurs = docteurService.findAll();
            return new ResponseEntity<>(allDocteurs, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/get/{id}")
    public Docteur getDocteurById(@PathVariable Long id) {
        return docteurService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Docteur> createDocteur(@RequestBody Docteur docteur) {
        Docteur savedDocteur = docteurService.saveDocteur(docteur);
        return new ResponseEntity<>(savedDocteur, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Docteur updateDocteur(@RequestBody Docteur docteur) {
        return docteurService.update(docteur.getId().longValue(), docteur);
    }

    @DeleteMapping(path = "/delete{id}")
    public void deleteDocteur(@PathVariable Long id) {
        docteurService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Docteur not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<Docteur> getDocteurByEmail(@PathVariable String email) {
        Docteur docteur = docteurService.findByEmail(email);
        return new ResponseEntity<>(docteur, HttpStatus.OK);
    }
}
