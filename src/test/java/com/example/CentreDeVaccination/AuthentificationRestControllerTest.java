package com.example.CentreDeVaccination;

import com.example.CentreDeVaccination.Controllers.AuthentificationRestController;
import com.example.CentreDeVaccination.Exceptions.ObjectNotFoundException;
import com.example.CentreDeVaccination.Models.Authentification;
import com.example.CentreDeVaccination.Services.AuthentificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthentificationRestControllerTest {

    @Mock
    private AuthentificationService authentificationService;

    @InjectMocks
    private AuthentificationRestController authentificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAuthentificationById() {
        // Mocking data
        Authentification mockAuthentification = new Authentification(1, "test@example.com", "password");

        // Mocking behavior
        when(authentificationService.findOneById(1L)).thenReturn(mockAuthentification);

        // Testing
        Authentification response = authentificationController.getauthentificationById(1L);

        // Verifying the result
        assertEquals(mockAuthentification, response);

        // Verifying that the service method was called
        verify(authentificationService, times(1)).findOneById(1L);
    }

    @Test
    void testCreateAuthentification() {
        // Mocking data
        Authentification mockAuthentification = new Authentification(1, "test@example.com", "password");

        // Mocking behavior
        when(authentificationService.saveAuthentification(any(Authentification.class))).thenReturn(mockAuthentification);

        // Testing
        ResponseEntity<Authentification> response = authentificationController.createAuthentification(mockAuthentification);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAuthentification, response.getBody());

        // Verifying that the service method was called
        verify(authentificationService, times(1)).saveAuthentification(any(Authentification.class));
    }

    @Test
    void testUpdateAuthentification() {
        // Mocking data
        Authentification mockAuthentification = new Authentification(1, "test@example.com", "password");

        // Mocking behavior
        when(authentificationService.update(anyLong(), any(Authentification.class))).thenReturn(mockAuthentification);

        // Testing
        Authentification response = authentificationController.updateAuthentification(mockAuthentification);

        // Verifying the result
        assertEquals(mockAuthentification, response);

        // Verifying that the service method was called
        verify(authentificationService, times(1)).update(anyLong(), any(Authentification.class));
    }

    @Test
    void testDeleteAuthentification() {
        // Testing
        authentificationController.deleteAuthentification(1L);

        // Verifying that the service method was called
        verify(authentificationService, times(1)).delete(1L);
    }

    @Test
    void testHandleObjectNotFoundException() {
        // Mocking behavior
        when(authentificationService.findOneById(anyLong())).thenThrow(new ObjectNotFoundException("Authentification not found"));

        // Testing
        ResponseEntity<String> response = authentificationController.handle(new ObjectNotFoundException("Authentification not found"));

        // Verifying the result
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Authentification not found: Authentification not found", response.getBody());
    }
}
