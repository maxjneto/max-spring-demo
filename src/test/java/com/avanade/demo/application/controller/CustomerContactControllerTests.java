package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.domain.service.CustomerContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerContactControllerTests {

    @Mock
    private CustomerContactService customerContactService;

    @InjectMocks
    private CustomerContactController customerContactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerContactsById_returnsContacts_whenValidId() {
        Long customerId = 1L;
        CustomerContactDTO contactDTO = new CustomerContactDTO("email", "johndoe@email.com");
        List<CustomerContactDTO> contacts = List.of(contactDTO);

        when(customerContactService.listCustomerContactsById(customerId)).thenReturn(contacts);

        List<CustomerContactDTO> result = customerContactController.getCustomerContactsById(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(contactDTO, result.get(0));
    }

    @Test
    void getCustomerContactsById_returnsEmptyList_whenNoContacts() {
        Long customerId = 1L;

        when(customerContactService.listCustomerContactsById(customerId)).thenReturn(Collections.emptyList());

        List<CustomerContactDTO> result = customerContactController.getCustomerContactsById(customerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCustomerContactsById_returnsEmptyList_whenInvalidId() {
        Long invalidCustomerId = -1L;

        when(customerContactService.listCustomerContactsById(invalidCustomerId)).thenReturn(Collections.emptyList());

        List<CustomerContactDTO> result = customerContactController.getCustomerContactsById(invalidCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
