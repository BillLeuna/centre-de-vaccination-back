package com.example.CentreDeVaccination.Controllers;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Authentification;
import com.example.CentreDeVaccination.Services.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentifications")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationRestController {

    @Autowired
    private AuthentificationService authentificationService;

    @GetMapping(path = "/get/{id}")
    public Authentification getauthentificationById(@PathVariable Long id) {
        return authentificationService.findOneById(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Authentification> createAuthentification(@RequestBody Authentification authentification) {
        Authentification savedAuthentification = authentificationService.saveAuthentification(authentification);
        return new ResponseEntity<>(savedAuthentification, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Authentification updateAuthentification(@RequestBody Authentification authentification) {
        return authentificationService.update(authentification.getId().longValue(), authentification);
    }

    @DeleteMapping(path = "/delete{id}")
    public void deleteAuthentification(@PathVariable Long id) {
        authentificationService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Authentification not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
