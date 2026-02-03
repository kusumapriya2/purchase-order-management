## 🛠️ Prerequisites

Before running the project, make sure you have the following installed and configured:

### 1. Java
- Java 17 or higher
- Check version:
```bash
java -version



# Purchase Order Management System 🧾

A Spring Boot REST API project to manage **Employees, Vendors, Products, and Purchase Orders** using **DTOs, MapStruct, Validation, Logging, and Exception Handling**.

---

## 📌 Project Overview

The **Purchase Order Management System** allows an organization to:

- Manage Employees
- Manage Vendors
- Manage Products
- Create and track Purchase Orders
- Maintain clean architecture using DTOs and Mappers
- Validate user input
- Handle errors globally
- Log application activity

This project follows **industry-level Spring Boot best practices**.

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Lombok
- Bean Validation (Jakarta Validation)
- SLF4J Logging
- REST APIs
- Maven

---

## 🏗️ Project Architecture





### Key Layers
- **Controller** → Handles HTTP requests and responses
- **Service** → Business logic, validations, try-catch, logging
- **Repository** → Database operations using JPA
- **DTO** → Data transfer between client and server
- **Mapper (MapStruct)** → Converts DTO ↔ Entity
- **Entity** → Database table mapping
- **Exception Handler** → Centralized error handling

---

## 📦 Modules

### 👤 Employee
- Create employee
- Get all employees
- Get employee by ID
- Update employee
- Delete employee

### 🏢 Vendor
- Create vendor
- Get all vendors
- Get vendor by ID
- Update vendor
- Delete vendor

### 📦 Product
- Create product
- Get all products
- Update product
- Delete product
- Validation for name and price

### 🧾 Purchase Order
- Create purchase order
- Assign employee and vendor
- Add multiple products
- Track order status

### 🧾 Payment Details
- Create payment details
- Assign transactionId and paymentMethod
- Add details

---

## 🔁 Entity Relationships

- **Employee → PurchaseOrder** → One-to-Many
- **Vendor → PurchaseOrder** → One-to-Many
- **PurchaseOrder → Product** → Many-to-Many
- **PurchaseOrder → PaymentDetails** → One-to-One

---

## 🧩 DTO & MapStruct

### Why DTO?
- Prevents exposing entity directly
- Improves security
- Clean API contracts

### Why MapStruct?
- Automatic mapping
- No boilerplate code
- Compile-time safety

Example:
```java
@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO toDto(Vendor vendor);
    Vendor toEntity(VendorDTO dto);
    void updateVendorFromDto(VendorDTO dto, @MappingTarget Vendor vendor);
}
