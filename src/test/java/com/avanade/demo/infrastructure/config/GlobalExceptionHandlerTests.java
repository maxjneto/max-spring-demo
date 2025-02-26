package com.avanade.demo.infrastructure.config;

import com.avanade.demo.domain.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTests {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        webRequest = mock(WebRequest.class);
    }

    @Test
    void handleGlobalException_returnsInternalServerError() {
        Exception exception = new Exception("Global error");

        ResponseEntity<?> response = globalExceptionHandler.handleGlobalException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Global error", response.getBody());
    }

    @Test
    void handleResourceNotFoundException_returnsNotFound() {
        EntityNotFoundException exception = new EntityNotFoundException("Resource not found");

        ResponseEntity<?> response = globalExceptionHandler.handleResourceNotFoundException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody());
    }

    @Test
    void handleCreateCustomerWithoutContactException_returnsBadRequest() {
        CreateCustomerWithoutContactException exception = new CreateCustomerWithoutContactException("No contact provided");

        ResponseEntity<?> response = globalExceptionHandler.handleCreateCustomerWithoutContactException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No contact provided", response.getBody());
    }

    @Test
    void handleInvalidArgumentException_returnsBadRequest() {
        InvalidArgumentException exception = new InvalidArgumentException("Invalid argument");

        ResponseEntity<?> response = globalExceptionHandler.handleInvalidArgumentException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody());
    }
}
