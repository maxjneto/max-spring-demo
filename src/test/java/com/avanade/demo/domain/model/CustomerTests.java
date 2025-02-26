package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTests {

    @Test
    void customerConstructor_setsFieldsCorrectly() {
        Segment segment = new Segment(1L,"Retail");
        Customer customer = new Customer("John Doe", segment);

        assertEquals("John Doe", customer.getName());
        assertEquals(segment, customer.getSegment());
    }

    @Test
    void setName_updatesName() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");

        assertEquals("Jane Doe", customer.getName());
    }

    @Test
    void setSegment_updatesSegment() {
        Customer customer = new Customer();
        Segment segment = new Segment(1L,"Corporate");
        customer.setSegment(segment);

        assertEquals(segment, customer.getSegment());
    }

    @Test
    void getId_returnsId() {
        Customer customer = new Customer();
        assertNull(customer.getId());
    }
}