package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.domain.exception.CreateCustomerWithoutContactException;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.exception.InvalidArgumentException;
import com.avanade.demo.domain.model.*;
import com.avanade.demo.infrastructure.adapter.output.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerPersistenceAdapterTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @Mock
    private CustomerContactRepository customerContactRepository;

    @Mock
    private CustomerDocumentRepository customerDocumentRepository;

    @Mock
    private CustomerContactTypeRepository customerContactTypeRepository;

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @InjectMocks
    private CustomerPersistenceAdapter adapter;

    public CustomerPersistenceAdapterTests() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createCustomer_throwsCreateCustomerWithoutContactException_whenContactsAreEmpty() {
        CreateCustomerDTO customerDTO = new CreateCustomerDTO("John Doe", "Retail", List.of(),"123456789");

        CreateCustomerWithoutContactException exception = assertThrows(CreateCustomerWithoutContactException.class, () -> {
            adapter.createCustomer(customerDTO);
        });

        assertEquals("Cliente deve ter um contato", exception.getMessage());
    }

    @Test
    void createCustomer_throwsEntityNotFoundException_whenSegmentNotFound() {
        CreateCustomerDTO customerDTO = new CreateCustomerDTO("John Doe", "NonExistentSegment", List.of(new CustomerContactDTO("e-mail", "john.doe@example.com")),"123456789");

        when(segmentRepository.findByName("NonExistentSegment")).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            adapter.createCustomer(customerDTO);
        });

        assertEquals("Segmento não encontrado", exception.getMessage());
    }

    @Test
    void createCustomer_createsCustomerSuccessfully() {
        CreateCustomerDTO customerDTO = new CreateCustomerDTO("John Doe", "Retail", List.of(new CustomerContactDTO("e-mail", "john.doe@example.com")),"123456789");
        Segment segment = new Segment(1L, "Retail");
        Customer customer = new Customer("John Doe", segment);
        DocumentType documentType = new DocumentType(1L, "CPF");
        CustomerDocument customerDocument = new CustomerDocument(customer, documentType, "123456789");
        CustomerContactType contactType = new CustomerContactType(1L, "e-mail");
        CustomerContact customerContact = new CustomerContact(customer, contactType, "john.doe@example.com");

        when(segmentRepository.findByName("Retail")).thenReturn(Optional.of(segment));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(documentTypeRepository.findByName("CPF")).thenReturn(Optional.of(documentType));
        when(customerDocumentRepository.save(any(CustomerDocument.class))).thenReturn(customerDocument);
        when(customerContactTypeRepository.findByName("e-mail")).thenReturn(Optional.of(contactType));
        when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(customerContact);

        CustomerDTO result = adapter.createCustomer(customerDTO);

        assertEquals("John Doe", result.name());
        assertEquals("Retail", result.segmentName());
        assertEquals(1, result.contacts().size());
        assertEquals("e-mail", result.contacts().getFirst().type());
        assertEquals("john.doe@example.com", result.contacts().getFirst().value());
        assertEquals(1, result.documents().size());
        assertEquals("123456789", result.documents().getFirst().documentNumber());
        assertEquals("CPF", result.documents().getFirst().documentType());
    }

    @Test
    void getCustomerById_throwsEntityNotFoundException_whenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            adapter.getCustomerById(1L);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    void getCustomerById_returnsCustomerDTO_whenCustomerFound() {
        Segment segment = new Segment(1L, "Retail");
        Customer customer = new Customer("John Doe", segment);
        customer.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDTO result = adapter.getCustomerById(1L);

        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("Retail", result.segmentName());
    }

    @Test
    void getCustomerByName_throwsEntityNotFoundException_whenCustomerNotFound() {
        when(customerRepository.findByName("John Doe")).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            adapter.getCustomerByName("John Doe");
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    void getCustomerByName_returnsCustomerDTO_whenCustomerFound() {
        Segment segment = new Segment(1L, "Retail");
        Customer customer = new Customer("John Doe", segment);
        customer.setId(1L);

        when(customerRepository.findByName("John Doe")).thenReturn(Optional.of(customer));

        CustomerDTO result = adapter.getCustomerByName("John Doe");

        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("Retail", result.segmentName());
    }

    @Test
    void listCustomerContactsById_returnsEmptyList_whenNoContactsFound() {
        when(customerContactRepository.findByCustomerId(1L)).thenReturn(List.of());

        List<CustomerContactDTO> result = adapter.listCustomerContactsById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void listCustomerContactsById_returnsContacts_whenContactsFound() {
        Customer customer = new Customer();
        customer.setId(1L);
        CustomerContactType contactType = new CustomerContactType(1L, "Email");
        CustomerContact contact = new CustomerContact(customer, contactType, "john.doe@example.com");

        when(customerContactRepository.findByCustomerId(1L)).thenReturn(List.of(contact));

        List<CustomerContactDTO> result = adapter.listCustomerContactsById(1L);

        assertEquals(1, result.size());
        assertEquals("Email", result.get(0).type());
        assertEquals("john.doe@example.com", result.get(0).value());
    }

    @Test
    void listCustomerDocumentsById_returnsEmptyList_whenNoDocumentsFound() {
        when(customerDocumentRepository.findByCustomerId(1L)).thenReturn(List.of());

        List<CustomerDocumentDTO> result = adapter.listCustomerDocumentsById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void listCustomerDocumentsById_returnsDocuments_whenDocumentsFound() {
        Customer customer = new Customer();
        customer.setId(1L);
        DocumentType documentType = new DocumentType(1L, "CPF");
        CustomerDocument document = new CustomerDocument(customer, documentType, "123456789");

        when(customerDocumentRepository.findByCustomerId(1L)).thenReturn(List.of(document));

        List<CustomerDocumentDTO> result = adapter.listCustomerDocumentsById(1L);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).documentNumber());
        assertEquals("CPF", result.get(0).documentType());
    }


}