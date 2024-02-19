package com.example.CentreDeVaccination.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Centre;
import com.example.CentreDeVaccination.Services.CentreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/centres")
@CrossOrigin(origins = "http://localhost:4200")
public class CentreRestController {

    @Autowired
    private CentreService centreService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Centre>> getAllCentres(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city) {

        List<Centre> filteredCentres;

        if (name != null && !name.isEmpty()) {
            // Filtrer par nom du centre
            filteredCentres = centreService.findAll()
                    .stream()
                    .filter(centre -> centre.getNom().startsWith(name))
                    .collect(Collectors.toList());
        } else if (city != null && !city.isEmpty()) {
            // Filtrer par ville dans l'adresse
            filteredCentres = centreService.findAll()
                    .stream()
                    .filter(centre -> centre.getAdresse().getVille().startsWith(city))
                    .collect(Collectors.toList());
        } else {
            // Si aucun paramètre 'name' ou 'city' n'est fourni, renvoyer la liste complète des centres
            filteredCentres = centreService.findAll();
        }

        return new ResponseEntity<>(filteredCentres, HttpStatus.OK);
    }



    @GetMapping(path = "/get/{id}")
    public Centre getCentreById(@PathVariable Long id) {
        return centreService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Centre> createCentre(@RequestBody Centre centre) {
        Centre savedCentre = centreService.saveCentre(centre);
        return new ResponseEntity<>(savedCentre, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Centre updateCentre(@RequestBody Centre centre) {
        return centreService.update(centre);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCentre(@PathVariable Long id) {
        centreService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Centre not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
