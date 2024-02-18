package com.example.CentreDeVaccination.Controllers;

import java.util.List;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Vaccination;
import com.example.CentreDeVaccination.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccinations")
@CrossOrigin(origins = "http://localhost:4200")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;
    
    @GetMapping("/get")
    public ResponseEntity<List<Vaccination>> getAllVaccinations() {
        List<Vaccination> vaccinations = vaccinationService.findAll();
        return new ResponseEntity<>(vaccinations, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Vaccination> getVaccinationById(@PathVariable Long id) {
        Vaccination vaccination = vaccinationService.findOneById(id);
        return new ResponseEntity<>(vaccination, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Vaccination> createVaccination(@RequestBody Vaccination vaccination) {
        Vaccination savedVaccination = vaccinationService.saveVaccination(vaccination);
        return new ResponseEntity<>(savedVaccination, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vaccination> updateVaccination(@PathVariable Long id, @RequestBody Vaccination updatedVaccination) {
        Vaccination vaccination = vaccinationService.update(id, updatedVaccination);
        return new ResponseEntity<>(vaccination, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVaccination(@PathVariable Long id) {
        vaccinationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Vaccination not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
