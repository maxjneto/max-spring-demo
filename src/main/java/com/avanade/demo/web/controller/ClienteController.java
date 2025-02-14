package com.avanade.demo.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    private static final Logger logger = LogManager.getLogger(ClienteController.class);

    @GetMapping("/cliente")
    public String getCliente(@RequestParam int codigo) {
        // Lógica para processar o código do cliente

        logger.info("Received client code: " + codigo);
        return "Cliente código: " + codigo;
    }
}