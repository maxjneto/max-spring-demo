package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDocumentTests {

    @Test
    void customerDocumentConstructor_setsFieldsCorrectly() {
        Customer customer = new Customer("John Doe", new Segment(1L,"Retail"));
        DocumentType documentType = new DocumentType(1L,"Passport");
        CustomerDocument document = new CustomerDocument(customer, documentType, "123456789");

        assertEquals(customer, document.getCustomer());
        assertEquals(documentType, document.getDocumentType());
        assertEquals("123456789", document.getDocument());
    }

    @Test
    void setId_updatesId() {
        CustomerDocument document = new CustomerDocument();
        document.setId(1L);

        assertEquals(1L, document.getId());
    }

    @Test
    void setCustomer_updatesCustomer() {
        CustomerDocument document = new CustomerDocument();
        Customer customer = new Customer("Jane Doe", new Segment(1L,"Corporate"));
        document.setCustomer(customer);

        assertEquals(customer, document.getCustomer());
    }

    @Test
    void setDocumentType_updatesDocumentType() {
        CustomerDocument document = new CustomerDocument();
        DocumentType documentType = new DocumentType(1L,"Driver's License");
        document.setDocumentType(documentType);

        assertEquals(documentType, document.getDocumentType());
    }

    @Test
    void setDocument_updatesDocument() {
        CustomerDocument document = new CustomerDocument();
        document.setDocument("987654321");

        assertEquals("987654321", document.getDocument());
    }

    @Test
    void getId_returnsNull_whenIdNotSet() {
        CustomerDocument document = new CustomerDocument();
        assertNull(document.getId());
    }

    @Test
    void getCustomer_returnsNull_whenCustomerNotSet() {
        CustomerDocument document = new CustomerDocument();
        assertNull(document.getCustomer());
    }

    @Test
    void getDocumentType_returnsNull_whenDocumentTypeNotSet() {
        CustomerDocument document = new CustomerDocument();
        assertNull(document.getDocumentType());
    }

    @Test
    void getDocument_returnsNull_whenDocumentNotSet() {
        CustomerDocument document = new CustomerDocument();
        assertNull(document.getDocument());
    }
}