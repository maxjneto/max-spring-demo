package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.domain.exception.CreateCustomerWithoutContactException;
import com.avanade.demo.domain.exception.InvalidArgumentException;
import com.avanade.demo.domain.service.CreateCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCustomerControllerTests {

    @Mock
    private CreateCustomerService createCustomerService;

    @InjectMocks
    private CreateCustomerController createCustomerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_returnsCreatedCustomer_whenValidInput() {
        CustomerContactDTO contactDTO = new CustomerContactDTO("email", "johndoe@email.com");

        CustomerDocumentDTO documentDTO = new CustomerDocumentDTO("CPF", "12345678910");

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("John Doe", "Classic",List.of(contactDTO),"12345678910");
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "Classic", List.of(documentDTO), List.of(contactDTO));
        when(createCustomerService.createCustomer(createCustomerDTO)).thenReturn(customerDTO);

        ResponseEntity<?> result = createCustomerController.createCustomer(createCustomerDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1L, ((CustomerDTO) result.getBody()).id());
        assertEquals("John Doe", ((CustomerDTO) result.getBody()).name());
        assertEquals("Classic", ((CustomerDTO) result.getBody()).segmentName());
        assertEquals(documentDTO, ((CustomerDTO) result.getBody()).documents().getFirst());
        assertEquals(contactDTO, ((CustomerDTO) result.getBody()).contacts().getFirst());
    }

    @Test
    void createCustomer_throwsException_whenNoName() {
        CustomerContactDTO contactDTO = new CustomerContactDTO("email", "johndoe@email.com");

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("", "Classic",List.of(contactDTO),"12345678910");

        doThrow(new InvalidArgumentException("Nome não pode ser vazio"))
                .when(createCustomerService).createCustomer(createCustomerDTO);

        assertThrows(InvalidArgumentException.class, () -> {
            createCustomerController.createCustomer(createCustomerDTO);
        });

    }

    @Test
    void createCustomer_throwsException_whenNoCpf() {
        CustomerContactDTO contactDTO = new CustomerContactDTO("email", "johndoe@email.com");

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("John Doe", "Classic",List.of(contactDTO),"");

        doThrow(new InvalidArgumentException("CPF não pode ser vazio"))
                .when(createCustomerService).createCustomer(createCustomerDTO);

        assertThrows(InvalidArgumentException.class, () -> {
            createCustomerController.createCustomer(createCustomerDTO);
        });

    }


    @Test
    void createCustomer_throwsException_whenNoContacts() {

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO("John Doe", "Classic",null,"12345678910");

        doThrow(new CreateCustomerWithoutContactException("Cliente deve ter um contato"))
                .when(createCustomerService).createCustomer(createCustomerDTO);

        assertThrows(CreateCustomerWithoutContactException.class, () -> {
            createCustomerController.createCustomer(createCustomerDTO);
        });

    }

}