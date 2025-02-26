package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerContactTypeTests {

    @Test
    void customerContactTypeConstructor_setsFieldsCorrectly() {
        CustomerContactType type = new CustomerContactType(1L, "Email");

        assertEquals(1L, type.getId());
        assertEquals("Email", type.getName());
    }

    @Test
    void setName_updatesName() {
        CustomerContactType type = new CustomerContactType();
        type.setName("Phone");

        assertEquals("Phone", type.getName());
    }

    @Test
    void setId_updatesId() {
        CustomerContactType type = new CustomerContactType();
        type.setId(2L);

        assertEquals(2L, type.getId());
    }

    @Test
    void getId_returnsNull_whenIdNotSet() {
        CustomerContactType type = new CustomerContactType();
        assertNull(type.getId());
    }

    @Test
    void getName_returnsNull_whenNameNotSet() {
        CustomerContactType type = new CustomerContactType();
        assertNull(type.getName());
    }
}