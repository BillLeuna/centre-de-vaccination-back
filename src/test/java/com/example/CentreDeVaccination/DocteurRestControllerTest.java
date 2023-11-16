package com.example.CentreDeVaccination;

import com.example.CentreDeVaccination.Controllers.DocteurRestController;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Docteur;
import com.example.CentreDeVaccination.Services.DocteurService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DocteurRestControllerTest {

    @Mock
    private DocteurService docteurService;

    @InjectMocks
    private DocteurRestController docteurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDocteurs() {
        // Mocking data
        List<Docteur> mockDocteurs = new ArrayList<>();
        mockDocteurs.add(new Docteur(1, "Dr. Smith", "John", Date.valueOf("1980-05-10"), "dr.smith@example.com", null, new ArrayList<>()));
        mockDocteurs.add(new Docteur(2, "Dr. Johnson", "Jane", Date.valueOf("1975-08-20"), "dr.johnson@example.com", null, new ArrayList<>()));

        // Mocking behavior
        when(docteurService.findAll()).thenReturn(mockDocteurs);

        // Testing
        ResponseEntity<List<Docteur>> response = docteurController.getAllDocteurs(null);

        // Verifying the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDocteurs, response.getBody());

        // Verifying that the service method was called
        verify(docteurService, times(1)).findAll();
    }

    @Test
    void testGetDocteurById() {
        // Mocking data
        Docteur mockDocteur = new Docteur(1, "Dr. Smith", "John", Date.valueOf("1980-05-10"), "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(docteurService.findOneById(1L)).thenReturn(mockDocteur);

        // Testing
        Docteur response = docteurController.getDocteurById(1L);

        // Verifying the result
        assertEquals(mockDocteur, response);

        // Verifying that the service method was called
        verify(docteurService, times(1)).findOneById(1L);
    }

    @Test
    void testCreateDocteur() {
        // Mocking data
        Docteur mockDocteur = new Docteur(1, "Dr. Smith", "John", Date.valueOf("1980-05-10"), "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(docteurService.saveDocteur(any(Docteur.class))).thenReturn(mockDocteur);

        // Testing
        ResponseEntity<Docteur> response = docteurController.createDocteur(mockDocteur);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockDocteur, response.getBody());

        // Verifying that the service method was called
        verify(docteurService, times(1)).saveDocteur(any(Docteur.class));
    }

    @Test
    void testUpdateDocteur() {
        // Mocking data
        Docteur mockDocteur = new Docteur(1, "Dr. Smith", "John", Date.valueOf("1980-05-10"), "dr.smith@example.com", null, new ArrayList<>());

        // Mocking behavior
        when(docteurService.update(anyLong(), any(Docteur.class))).thenReturn(mockDocteur);

        // Testing
        Docteur response = docteurController.updateDocteur(mockDocteur);

        // Verifying the result
        assertEquals(mockDocteur, response);

        // Verifying that the service method was called
        verify(docteurService, times(1)).update(anyLong(), any(Docteur.class));
    }

    @Test
    void testDeleteDocteur() {
        // Testing
        docteurController.deleteDocteur(1L);

        // Verifying that the service method was called
        verify(docteurService, times(1)).delete(1L);
    }

    @Test
    void testHandleObjectNotFoundException() {
        // Mocking behavior
        when(docteurService.findOneById(anyLong())).thenThrow(new ObjectNotFoundException("Docteur not found"));

        // Testing
        ResponseEntity<String> response = docteurController.handle(new ObjectNotFoundException("Docteur not found"));

        // Verifying the result
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Docteur not found: Docteur not found", response.getBody());
    }
}
