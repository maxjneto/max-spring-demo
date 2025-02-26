package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerDocumentDTO;
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

class CustomerDocumentServiceTests {

    @Mock
    private CustomerOutput customerOutput;

    @InjectMocks
    private CustomerDocumentService customerDocumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listCustomerDocumentsById_returnsDocuments_whenValidId() {
        Long customerId = 1L;
        CustomerDocumentDTO documentDTO = new CustomerDocumentDTO("CPF", "12345678910");
        List<CustomerDocumentDTO> documents = List.of(documentDTO);

        when(customerOutput.listCustomerDocumentsById(customerId)).thenReturn(documents);

        List<CustomerDocumentDTO> result = customerDocumentService.listCustomerDocumentsById(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(documentDTO, result.get(0));
    }

    @Test
    void listCustomerDocumentsById_returnsEmptyList_whenNoDocuments() {
        Long customerId = 1L;

        when(customerOutput.listCustomerDocumentsById(customerId)).thenReturn(Collections.emptyList());

        List<CustomerDocumentDTO> result = customerDocumentService.listCustomerDocumentsById(customerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void listCustomerDocumentsById_returnsEmptyList_whenInvalidId() {
        Long invalidCustomerId = -1L;

        when(customerOutput.listCustomerDocumentsById(invalidCustomerId)).thenReturn(Collections.emptyList());

        List<CustomerDocumentDTO> result = customerDocumentService.listCustomerDocumentsById(invalidCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}