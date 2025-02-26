package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.*;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.CreateCustomerWithoutContactException;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.exception.InvalidArgumentException;
import com.avanade.demo.domain.model.*;
import com.avanade.demo.infrastructure.adapter.output.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

    @Autowired
    private CustomerContactTypeRepository customerContactTypeRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer cust = customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return toCustomerDTO(cust,
                customerDocumentRepository.findByCustomerId(cust.getId()).stream().map(this::toCustomerDocumentDTO).toList(),
                customerContactRepository.findByCustomerId(cust.getId()).stream().map(this::toCustomerContactDTO).toList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        final Customer cust = customerRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return toCustomerDTO(cust,
                customerDocumentRepository.findByCustomerId(cust.getId()).stream().map(this::toCustomerDocumentDTO).toList(),
                customerContactRepository.findByCustomerId(cust.getId()).stream().map(this::toCustomerContactDTO).toList());
    }

    @Override
    public CustomerDTO createCustomer(CreateCustomerDTO customer) {
        if(customer.contacts() != null && !customer.contacts().isEmpty()){
            final Segment segment = segmentRepository.findByName(customer.segmentName())
                    .orElseThrow(() -> new EntityNotFoundException("Segmento não encontrado"));

            if(customer.name().isBlank()){
                throw new InvalidArgumentException("Nome não pode ser vazio");
            }

            if(customer.cpf().isBlank()){
                throw new InvalidArgumentException("CPF não pode ser vazio");
            }

            final Customer cust = customerRepository.save(new Customer(customer.name(),segment));

            final DocumentType documentType = documentTypeRepository.findByName("CPF").orElseThrow(() ->
                    new EntityNotFoundException("Tipo de documento não encontrado"));
            final CustomerDocument cpf = customerDocumentRepository.save(new CustomerDocument(cust,documentType,customer.cpf()));

            List<CustomerContactDTO> contacts = new ArrayList<>();
            for(CustomerContactDTO dto : customer.contacts()){
                final CustomerContactType contactType = customerContactTypeRepository.findByName(dto.type()).orElseThrow(() ->
                        new EntityNotFoundException("Tipo de contato não encontrado"));

                customerContactRepository.save(new CustomerContact(cust,contactType,dto.value()));
                contacts.add(new CustomerContactDTO(contactType.getName(),dto.value()));
            }
            return toCustomerDTO(cust,List.of(toCustomerDocumentDTO(cpf)), contacts);
        }else{
            throw new CreateCustomerWithoutContactException("Cliente deve ter um contato");
        }
    }

    @Override
    public List<CustomerContactDTO> listCustomerContactsById(Long id) {
        List<CustomerContact> contacts = customerContactRepository.findByCustomerId(id);
        return contacts.stream().map(this::toCustomerContactDTO).toList();
    }

    @Override
    public List<CustomerDocumentDTO> listCustomerDocumentsById(Long id) {
        List<CustomerDocument> documents = customerDocumentRepository.findByCustomerId(id);
        return documents.stream().map(this::toCustomerDocumentDTO).toList();
    }

    @Override
    public CustomerDocumentDTO addDocument(AddContactOrDocumentDTO document) {
        final Customer cust = customerRepository.findById(document.customerId()).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));
        final DocumentType documentType = documentTypeRepository.findByName(document.type()).orElseThrow(() ->
                new EntityNotFoundException("Tipo de documento não encontrado"));
        final CustomerDocument customerDocument = customerDocumentRepository.save(new CustomerDocument(cust,documentType,document.value()));
        return toCustomerDocumentDTO(customerDocument);
    }

    @Override
    public CustomerContactDTO addContact(AddContactOrDocumentDTO contact) {
        final Customer cust = customerRepository.findById(contact.customerId()).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));
        final CustomerContactType contactType = customerContactTypeRepository.findByName(contact.type()).orElseThrow(() ->
                new EntityNotFoundException("Tipo de contato não encontrado"));
        final CustomerContact customerContact = customerContactRepository.save(new CustomerContact(cust,contactType,contact.value()));
        return toCustomerContactDTO(customerContact);
    }

    private CustomerDocumentDTO toCustomerDocumentDTO(CustomerDocument document) {
        return new CustomerDocumentDTO(document.getDocument(), document.getDocumentType().getName());
    }

    private CustomerContactDTO toCustomerContactDTO(CustomerContact contact) {
        return new CustomerContactDTO(contact.getCustomerContactType().getName(), contact.getContactValue());
    }

    private CustomerDTO toCustomerDTO(Customer customer,List<CustomerDocumentDTO> documents, List<CustomerContactDTO> contacts) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getSegment().getName(),documents, contacts);
    }

}
