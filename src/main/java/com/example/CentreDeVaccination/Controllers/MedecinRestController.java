package com.example.CentreDeVaccination.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.CentreDeVaccination.Models.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Services.MedecinService;

@RestController
@RequestMapping("/medecins")
@CrossOrigin(origins = "http://localhost:4200")
public class MedecinRestController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Medecin>> getAllMedecins(@RequestParam(required = false) String nom) {
        if (nom != null && !nom.isEmpty()) {
            // Filtrer la liste des medecins par nom qui commence par la valeur du paramètre
            // 'nom'
            List<Medecin> filteredMedecins = medecinService.findAll()
                    .stream()
                    .filter(medecin -> medecin.getNom().startsWith(nom))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredMedecins, HttpStatus.OK);
        } else {
            // Si aucun paramètre 'nom' n'est fourni, renvoyer la liste complète des
            // medecins
            List<Medecin> allMedecins = medecinService.findAll();
            return new ResponseEntity<>(allMedecins, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/get/{id}")
    public Medecin getMedecinById(@PathVariable Long id) {
        return medecinService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Medecin> createMedecin(@RequestBody Medecin medecin) {
        Medecin savedMedecin = medecinService.saveMedecin(medecin);
        return new ResponseEntity<>(savedMedecin, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Medecin updateMedecin(@RequestBody Medecin medecin) {
        return medecinService.update(medecin.getId().longValue(), medecin);
    }

    @DeleteMapping(path = "/delete{id}")
    public void deleteMedecin(@PathVariable Long id) {
        medecinService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Medecin not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<Medecin> getMedecinByEmail(@PathVariable String email) {
        Medecin medecin = medecinService.findByEmail(email);
        return new ResponseEntity<>(medecin, HttpStatus.OK);
    }
}
