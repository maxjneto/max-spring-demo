package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerContactServiceTests {

    @Mock
    private CustomerOutput customerOutput;

    @InjectMocks
    private CustomerContactService customerContactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listCustomerContactsById_returnsContacts_whenValidId() {
        Long customerId = 1L;
        CustomerContactDTO contactDTO = new CustomerContactDTO("email", "johndoe@email.com");
        List<CustomerContactDTO> contacts = List.of(contactDTO);

        when(customerOutput.listCustomerContactsById(customerId)).thenReturn(contacts);

        List<CustomerContactDTO> result = customerContactService.listCustomerContactsById(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(contactDTO, result.get(0));
    }

    @Test
    void listCustomerContactsById_returnsEmptyList_whenNoContacts() {
        Long customerId = 1L;

        when(customerOutput.listCustomerContactsById(customerId)).thenReturn(Collections.emptyList());

        List<CustomerContactDTO> result = customerContactService.listCustomerContactsById(customerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void listCustomerContactsById_returnsEmptyList_whenInvalidId() {
        Long invalidCustomerId = -1L;

        when(customerOutput.listCustomerContactsById(invalidCustomerId)).thenReturn(Collections.emptyList());

        List<CustomerContactDTO> result = customerContactService.listCustomerContactsById(invalidCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}