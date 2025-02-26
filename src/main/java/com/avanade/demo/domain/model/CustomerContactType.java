package com.avanade.demo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_contacts_types")
public class CustomerContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public CustomerContactType(){}

    public CustomerContactType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

}