package com.avanade.demo.application.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

public record CreateCustomerDTO (String name , String segmentName, List<CustomerContactDTO> contacts,String cpf) {
}