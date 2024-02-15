package com.example.CentreDeVaccination.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Services.PatientService;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Patient>> getAllPatients(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            // Filtrer la liste des patients par nom qui commence par la valeur du paramètre
            // 'name'
            List<Patient> filteredPatients = patientService.findAll()
                    .stream()
                    .filter(patient -> patient.getPrenom().startsWith(name))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredPatients, HttpStatus.OK);
        } else {
            // Si aucun paramètre 'name' n'est fourni, renvoyer la liste complète des
            // patients
            List<Patient> allPatients = patientService.findAll();
            return new ResponseEntity<>(allPatients, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/get/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.findOneById(id);
    }

    @PostMapping(path = "/create", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.update(patient.getId().longValue(), patient);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ObjectNotFoundException ex) {
        return new ResponseEntity<>("Patient not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getMedecin/{id}")
    public List<Medecin> getMedecins(@PathVariable Long id) {
        return patientService.getMedecins(id);
    }

    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        Patient patient = patientService.findByEmail(email);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
