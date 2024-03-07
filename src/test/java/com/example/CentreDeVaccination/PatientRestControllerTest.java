package com.example.CentreDeVaccination;

import com.example.CentreDeVaccination.Controllers.PatientRestController;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Models.Patient;
import com.example.CentreDeVaccination.Services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientRestControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientRestController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPatients() {
        // Mocking data
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient(1L, "John", "Doe", Date.valueOf("1990-01-01"), Date.valueOf("1990-01-01"), "john@example.com", null, null, null, null));
        mockPatients.add(new Patient(2L, "Jane", "Smith", Date.valueOf("1990-01-01"), Date.valueOf("1985-05-15"), "jane@example.com", null, null, null, null));

        // Mocking behavior
        when(patientService.findAll()).thenReturn(mockPatients);

        // Testing
        ResponseEntity<List<Patient>> response = patientController.getAllPatients(null);

        // Verifying the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPatients, response.getBody());

        // Verifying that the service method was called
        verify(patientService, times(1)).findAll();
    }

    @Test
    void testGetPatientById() {
        // Mocking data
        Patient mockPatient = new Patient(1L, "John", "Doe", Date.valueOf("1990-01-01"), Date.valueOf("1990-01-01"), "john@example.com", null, null, null, null);

        // Mocking behavior
        when(patientService.findOneById(1L)).thenReturn(mockPatient);

        // Testing
        Patient response = patientController.getPatientById(1L);

        // Verifying the result
        assertEquals(mockPatient, response);

        // Verifying that the service method was called
        verify(patientService, times(1)).findOneById(1L);
    }

    @Test
    void testCreatePatient() {
        // Mocking data
        Patient mockPatient = new Patient(1L, "John", "Doe", Date.valueOf("1990-01-01"), Date.valueOf("1990-01-01"), "john@example.com", null, null, null, null);

        // Mocking behavior
        when(patientService.savePatient(any(Patient.class))).thenReturn(mockPatient);

        // Testing
        ResponseEntity<Patient> response = patientController.createPatient(mockPatient);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockPatient, response.getBody());

        // Verifying that the service method was called
        verify(patientService, times(1)).savePatient(any(Patient.class));
    }

    @Test
    void testUpdatePatient() {
        // Mocking data
        Patient mockPatient = new Patient(1L, "John", "Doe", Date.valueOf("1990-01-01"), Date.valueOf("1990-01-01"), "john@example.com", null, null, null, null);

        // Mocking behavior
        when(patientService.update(any(Patient.class))).thenReturn(mockPatient);

        // Testing
        Patient response = patientController.updatePatient(mockPatient);

        // Verifying the result
        assertEquals(mockPatient, response);

        // Verifying that the service method was called
        verify(patientService, times(1)).update(any(Patient.class));
    }

    @Test
    void testDeletePatient() {
        // Testing
        patientController.deletePatient(1L);

        // Verifying that the service method was called
        verify(patientService, times(1)).delete(1L);
    }

    @Test
    void testHandleObjectNotFoundException() {
        // Mocking behavior
        when(patientService.findOneById(anyLong())).thenThrow(new ObjectNotFoundException("Patient not found"));

        // Testing
        ResponseEntity<String> response = patientController.handle(new ObjectNotFoundException("Patient not found"));

        // Verifying the result
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient not found: Patient not found", response.getBody());
    }

}

