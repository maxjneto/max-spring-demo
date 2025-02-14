package com.avanade.demo.infrastructure.repository;

import com.avanade.demo.core.domain.CustomerContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactTypeRepository extends JpaRepository<CustomerContactType, Long> {
}