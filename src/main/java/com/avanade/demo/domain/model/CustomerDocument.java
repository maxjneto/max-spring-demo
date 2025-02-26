package com.avanade.demo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_documents")
public class CustomerDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String document;

    public CustomerDocument(Customer customer, DocumentType documentType, String document) {
        this.customer = customer;
        this.documentType = documentType;
        this.document = document;
    }

    public CustomerDocument() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    public DocumentType getDocumentType() {return documentType;}

    public void setDocumentType(DocumentType documentType) {this.documentType = documentType;}

    public String getDocument() {return document;}

    public void setDocument(String document) {this.document = document;}

}