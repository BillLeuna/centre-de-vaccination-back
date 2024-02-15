package com.example.CentreDeVaccination;

import com.example.CentreDeVaccination.Controllers.AdresseRestController;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Adresse;
import com.example.CentreDeVaccination.Services.AdresseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdresseRestControllerTest {

    @Mock
    private AdresseService adresseService;

    @InjectMocks
    private AdresseRestController adresseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAdresses() {
        // Mocking data
        List<Adresse> mockAdresses = new ArrayList<>();
        mockAdresses.add(new Adresse(1, "Paris", "Rue de la Paix", 75001, null, null, null));
        mockAdresses.add(new Adresse(2, "Lyon", "Rue Bellecour", 69002, null, null, null));

        // Mocking behavior
        when(adresseService.findAll()).thenReturn(mockAdresses);

        // Testing
        ResponseEntity<List<Adresse>> response = adresseController.getAllAdresses();

        // Verifying the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAdresses, response.getBody());

        // Verifying that the service method was called
        verify(adresseService, times(1)).findAll();
    }

    @Test
    void testGetAdresseById() {
        // Mocking data
        Adresse mockAdresse = new Adresse(1, "Paris", "Rue de la Paix", 75001, null, null, null);

        // Mocking behavior
        when(adresseService.findOneById(1L)).thenReturn(mockAdresse);

        // Testing
        Adresse response = adresseController.getAdresseById(1L);

        // Verifying the result
        assertEquals(mockAdresse, response);

        // Verifying that the service method was called
        verify(adresseService, times(1)).findOneById(1L);
    }

    @Test
    void testCreateAdresse() {
        // Mocking data
        Adresse mockAdresse = new Adresse(1, "Paris", "Rue de la Paix", 75001, null, null, null);

        // Mocking behavior
        when(adresseService.saveAdresse(any(Adresse.class))).thenReturn(mockAdresse);

        // Testing
        ResponseEntity<Adresse> response = adresseController.createAdresse(mockAdresse);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAdresse, response.getBody());

        // Verifying that the service method was called
        verify(adresseService, times(1)).saveAdresse(any(Adresse.class));
    }

    @Test
    void testUpdateAdresse() {
        // Mocking data
        Adresse mockAdresse = new Adresse(1, "Paris", "Rue de la Paix", 75001, null, null, null);

        // Mocking behavior
        when(adresseService.update(anyLong(), any(Adresse.class))).thenReturn(mockAdresse);

        // Testing
        Adresse response = adresseController.updateAdresse(mockAdresse);

        // Verifying the result
        assertEquals(mockAdresse, response);

        // Verifying that the service method was called
        verify(adresseService, times(1)).update(anyLong(), any(Adresse.class));
    }

    @Test
    void testDeleteAdresse() {
        // Testing
        adresseController.deleteAdresse(1L);

        // Verifying that the service method was called
        verify(adresseService, times(1)).delete(1L);
    }

    @Test
    void testHandleObjectNotFoundException() {
        // Mocking behavior
        when(adresseService.findOneById(anyLong())).thenThrow(new ObjectNotFoundException("Adresse not found"));

        // Testing
        ResponseEntity<String> response = adresseController.handle(new ObjectNotFoundException("Adresse not found"));

        // Verifying the result
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Adresse not found: Adresse not found", response.getBody());
    }
}
