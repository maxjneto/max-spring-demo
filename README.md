# Customer Management API

This project is a Customer Management API built with Java, Spring Boot, and Maven. It follows the Hexagonal Architecture pattern to ensure a clean separation of concerns.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)

## Architecture

The project is organized following the Hexagonal Architecture (Ports and Adapters) pattern. The main packages are:

- **domain**: Contains the core business logic and domain entities.
- **application**: Contains the application logic, including use cases and DTOs.
- **infrastructure**: Contains the implementation of the output ports, such as repositories and external services.
- **adapter**: Contains the input and output adapters, such as controllers and API clients.

## Endpoints

The API provides the following endpoints:

- `GET /customer/{id}`: Get customer by ID.
- `GET /cliente/name/{customerName}`: Get customer by name.
- `GET /cliente/documents/{id}`: Get customer documents by ID.
- `GET /cliente/contacts/{id}`: Get customer contacts by ID.
- `POST /customer`: Save a new customer.
- `POST /customer/contact`: Add a new contact to an existing customer.
- `POST /customer/document`: Add a new document to an existing customer.

Refer to the `openapi.yaml` file for detailed API specifications.
