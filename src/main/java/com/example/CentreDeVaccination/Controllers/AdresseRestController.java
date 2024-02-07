package com.example.CentreDeVaccination.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Adresse;
import com.example.CentreDeVaccination.Services.AdresseService;

@RestController
@RequestMapping("/adresses")
@CrossOrigin(origins = "http://localhost:4200")
public class AdresseRestController {

    @Autowired
    private AdresseService adresseService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> allAdresses = adresseService.findAll();
        return new ResponseEntity<>(allAdresses, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    public Adresse getAdresseById(@PathVariable Long id) {
        return adresseService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        return new ResponseEntity<>(savedAdresse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Adresse updateAdresse(@RequestBody Adresse adresse) {
        return adresseService.update(adresse.getId().longValue(), adresse);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAdresse(@PathVariable Long id) {
        adresseService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Adresse not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
