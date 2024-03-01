package com.example.CentreDeVaccination.Controllers;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.AdministrateurCentre;
import com.example.CentreDeVaccination.Services.AdministrateurCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrateursCentres")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministrateurCentreRestController {

    @Autowired
    private AdministrateurCentreService administrateurCentreService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<AdministrateurCentre>> getAllAdministrateursCentres(@RequestParam(required = false) String nom) {
        if (nom != null && !nom.isEmpty()) {
            // Filtrer la liste des administrateursCentres par nom qui commence par la valeur du paramètre
            // 'nom'
            List<AdministrateurCentre> filteredAdministrateursCentres = administrateurCentreService.findAll()
                    .stream()
                    .filter(administrateurCentre -> administrateurCentre.getNom().startsWith(nom))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredAdministrateursCentres, HttpStatus.OK);
        } else {
            // Si aucun paramètre 'name' n'est fourni, renvoyer la liste complète des
            // administrateursCentres
            List<AdministrateurCentre> allAdministrateursCentres = administrateurCentreService.findAll();
            return new ResponseEntity<>(allAdministrateursCentres, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/get/{id}")
    public AdministrateurCentre getAdministrateurCentreById(@PathVariable Long id) {
        return administrateurCentreService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<AdministrateurCentre> createAdministrateurCentre(@RequestBody AdministrateurCentre administrateurCentre) {
        AdministrateurCentre savedAdministrateurCentre = administrateurCentreService.saveAdministrateurCentre(administrateurCentre);
        return new ResponseEntity<>(savedAdministrateurCentre, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public AdministrateurCentre updateAdministrateurCentre(@RequestBody AdministrateurCentre administrateurCentre) {
        return administrateurCentreService.update(administrateurCentre);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAdministrateurCentre(@PathVariable Long id) {
        administrateurCentreService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("AdministrateurCentre not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<AdministrateurCentre> getAdministrateurCentreByEmail(@PathVariable String email) {
        AdministrateurCentre administrateurCentre = administrateurCentreService.findByEmail(email);
        return new ResponseEntity<>(administrateurCentre, HttpStatus.OK);
    }
}
