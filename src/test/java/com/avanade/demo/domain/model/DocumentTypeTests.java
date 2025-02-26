package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTypeTests {

    @Test
    void documentTypeConstructor_setsFieldsCorrectly() {
        DocumentType documentType = new DocumentType(1L, "Passport");

        assertEquals(1L, documentType.getId());
        assertEquals("Passport", documentType.getName());
    }

    @Test
    void setName_updatesName() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Driver's License");

        assertEquals("Driver's License", documentType.getName());
    }

    @Test
    void setId_updatesId() {
        DocumentType documentType = new DocumentType();
        documentType.setId(2L);

        assertEquals(2L, documentType.getId());
    }

    @Test
    void getId_returnsNull_whenIdNotSet() {
        DocumentType documentType = new DocumentType();
        assertNull(documentType.getId());
    }

    @Test
    void getName_returnsNull_whenNameNotSet() {
        DocumentType documentType = new DocumentType();
        assertNull(documentType.getName());
    }
}