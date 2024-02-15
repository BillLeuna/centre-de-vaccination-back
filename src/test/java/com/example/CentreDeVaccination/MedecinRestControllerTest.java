package com.example.CentreDeVaccination;

import com.example.CentreDeVaccination.Controllers.MedecinRestController;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Medecin;
import com.example.CentreDeVaccination.Services.MedecinService;
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

class MedecinRestControllerTest {

    @Mock
    private MedecinService medecinService;

    @InjectMocks
    private MedecinRestController medecinController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMedecins() {
        // Mocking data
        List<Medecin> mockMedecins = new ArrayList<>();
        mockMedecins.add(new Medecin(1, "Dr. Smith", "John", "dr.smith@example.com", "07 66 77 88 33", null, new ArrayList<>()));
        mockMedecins.add(new Medecin(2, "Dr. Johnson", "Jane", "07 66 77 99 33", "dr.johnson@example.com", null, new ArrayList<>()));

        // Mocking behavior
        when(medecinService.findAll()).thenReturn(mockMedecins);

        // Testing
        ResponseEntity<List<Medecin>> response = medecinController.getAllMedecins(null);

        // Verifying the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMedecins, response.getBody());

        // Verifying that the service method was called
        verify(medecinService, times(1)).findAll();
    }

    @Test
    void testGetMedecinById() {
        // Mocking data
        Medecin mockMedecin = new Medecin(1, "Dr. Smith", "John", "07 66 77 88 33", "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(medecinService.findOneById(1L)).thenReturn(mockMedecin);

        // Testing
        Medecin response = medecinController.getMedecinById(1L);

        // Verifying the result
        assertEquals(mockMedecin, response);

        // Verifying that the service method was called
        verify(medecinService, times(1)).findOneById(1L);
    }

    @Test
    void testCreateMedecin() {
        // Mocking data
        Medecin mockMedecin = new Medecin(1, "Dr. Smith", "John", "07 66 77 88 33", "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(medecinService.saveMedecin(any(Medecin.class))).thenReturn(mockMedecin);

        // Testing
        ResponseEntity<Medecin> response = medecinController.createMedecin(mockMedecin);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockMedecin, response.getBody());

        // Verifying that the service method was called
        verify(medecinService, times(1)).saveMedecin(any(Medecin.class));
    }

    @Test
    void testUpdateMedecin() {
        // Mocking data
        Medecin mockMedecin = new Medecin(1, "Dr. Smith", "John", "07 66 77 88 33", "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(medecinService.update(anyLong(), any(Medecin.class))).thenReturn(mockMedecin);

        // Testing
        Medecin response = medecinController.updateMedecin(mockMedecin);

        // Verifying the result
        assertEquals(mockMedecin, response);

        // Verifying that the service method was called
        verify(medecinService, times(1)).update(anyLong(), any(Medecin.class));
    }

    @Test
    void testDeleteMedecin() {
        // Testing
        medecinController.deleteMedecin(1L);

        // Verifying that the service method was called
        verify(medecinService, times(1)).delete(1L);
    }

    @Test
    void testHandleObjectNotFoundException() {
        // Mocking behavior
        when(medecinService.findOneById(anyLong())).thenThrow(new ObjectNotFoundException("Medecin not found"));

        // Testing
        ResponseEntity<String> response = medecinController.handle(new ObjectNotFoundException("Medecin not found"));

        // Verifying the result
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Medecin not found: Medecin not found", response.getBody());
    }
}
