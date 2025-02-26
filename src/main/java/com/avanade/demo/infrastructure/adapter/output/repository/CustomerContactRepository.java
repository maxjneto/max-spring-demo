package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.Customer;
import com.avanade.demo.domain.model.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {

    List<CustomerContact> findByCustomerId(Long id);

}