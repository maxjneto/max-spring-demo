package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerContactTests {

    @Test
    void customerContactConstructor_setsFieldsCorrectly() {
        Customer customer = new Customer("John Doe", new Segment(1L,"Retail"));
        CustomerContactType type = new CustomerContactType(1L,"Email");
        CustomerContact contact = new CustomerContact(customer, type, "john.doe@example.com");

        assertEquals(customer, contact.getCustomer());
        assertEquals(type, contact.getCustomerContactType());
        assertEquals("john.doe@example.com", contact.getContactValue());
    }

    @Test
    void setId_updatesId() {
        CustomerContact contact = new CustomerContact();
        contact.setId(1L);

        assertEquals(1L, contact.getId());
    }

    @Test
    void setCustomer_updatesCustomer() {
        CustomerContact contact = new CustomerContact();
        Customer customer = new Customer("Jane Doe", new Segment(1L,"Corporate"));
        contact.setCustomer(customer);

        assertEquals(customer, contact.getCustomer());
    }

    @Test
    void setCustomerContactType_updatesType() {
        CustomerContact contact = new CustomerContact();
        CustomerContactType type = new CustomerContactType(1L,"Phone");
        contact.setCustomerContactType(type);

        assertEquals(type, contact.getCustomerContactType());
    }

    @Test
    void setContactValue_updatesContactValue() {
        CustomerContact contact = new CustomerContact();
        contact.setContactValue("jane.doe@example.com");

        assertEquals("jane.doe@example.com", contact.getContactValue());
    }

    @Test
    void getId_returnsNull_whenIdNotSet() {
        CustomerContact contact = new CustomerContact();
        assertNull(contact.getId());
    }

    @Test
    void getCustomer_returnsNull_whenCustomerNotSet() {
        CustomerContact contact = new CustomerContact();
        assertNull(contact.getCustomer());
    }

    @Test
    void getCustomerContactType_returnsNull_whenTypeNotSet() {
        CustomerContact contact = new CustomerContact();
        assertNull(contact.getCustomerContactType());
    }

    @Test
    void getContactValue_returnsNull_whenContactValueNotSet() {
        CustomerContact contact = new CustomerContact();
        assertNull(contact.getContactValue());
    }

}