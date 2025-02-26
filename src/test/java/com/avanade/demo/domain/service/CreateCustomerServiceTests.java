package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCustomerServiceTests {

    @Mock
    private CustomerOutput customerOutput;

    @InjectMocks
    private CreateCustomerService createCustomerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_returnsCustomer_whenValidInput() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("John Doe", "Classic", null, "12345678910");
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "Classic", null, null);

        when(customerOutput.createCustomer(createCustomerDTO)).thenReturn(customerDTO);

        CustomerDTO result = createCustomerService.createCustomer(createCustomerDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
    }

    @Test
    void createCustomer_throwsException_whenInvalidInput() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(null, "Classic", null, "12345678910");

        when(customerOutput.createCustomer(createCustomerDTO)).thenThrow(new IllegalArgumentException("Invalid input"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createCustomerService.createCustomer(createCustomerDTO);
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void createCustomer_returnsNull_whenCustomerOutputReturnsNull() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("John Doe", "Classic", null, "12345678910");

        when(customerOutput.createCustomer(createCustomerDTO)).thenReturn(null);

        CustomerDTO result = createCustomerService.createCustomer(createCustomerDTO);

        assertNull(result);
    }
}
