package com.avanade.demo.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer_contacts")
public class CustomerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_contact_type_id", nullable = false)
    private CustomerContactType customerContactType;

    @Column(nullable = false)
    private String contactValue;

    public CustomerContact(){}

    public CustomerContact(Customer customer,CustomerContactType type, String contactValue) {
        this.customer = customer;
        this.customerContactType = type;
        this.contactValue = contactValue;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    public CustomerContactType getCustomerContactType() {return customerContactType;}

    public void setCustomerContactType(CustomerContactType customerContactType) {this.customerContactType = customerContactType;}

    public String getContactValue() {return contactValue;}

    public void setContactValue(String contactValue) {this.contactValue = contactValue;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerContact that = (CustomerContact) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(customerContactType, that.customerContactType) && Objects.equals(contactValue, that.contactValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, customerContactType, contactValue);
    }

    @Override
    public String toString() {
        return "CustomerContact{" +
                "id=" + id +
                ", customer=" + customer +
                ", customerContactType=" + customerContactType +
                ", contactValue='" + contactValue + '\'' +
                '}';
    }

}