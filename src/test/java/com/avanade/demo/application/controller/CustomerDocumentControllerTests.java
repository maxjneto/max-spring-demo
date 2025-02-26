package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.domain.service.CustomerDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerDocumentControllerTest {

    @Mock
    private CustomerDocumentService customerDocumentService;

    @InjectMocks
    private CustomerDocumentController customerDocumentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerDocumentsById_returnsDocuments_whenValidId() {
        Long customerId = 1L;
        CustomerDocumentDTO documentDTO = new CustomerDocumentDTO("CPF", "12345678910");
        List<CustomerDocumentDTO> documents = List.of(documentDTO);

        when(customerDocumentService.listCustomerDocumentsById(customerId)).thenReturn(documents);

        List<CustomerDocumentDTO> result = customerDocumentController.getCustomerDocumentsById(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(documentDTO, result.get(0));
    }

    @Test
    void getCustomerDocumentsById_returnsEmptyList_whenNoDocuments() {
        Long customerId = 1L;

        when(customerDocumentService.listCustomerDocumentsById(customerId)).thenReturn(Collections.emptyList());

        List<CustomerDocumentDTO> result = customerDocumentController.getCustomerDocumentsById(customerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCustomerDocumentsById_returnsEmptyList_whenInvalidId() {
        Long invalidCustomerId = -1L;

        when(customerDocumentService.listCustomerDocumentsById(invalidCustomerId)).thenReturn(Collections.emptyList());

        List<CustomerDocumentDTO> result = customerDocumentController.getCustomerDocumentsById(invalidCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}