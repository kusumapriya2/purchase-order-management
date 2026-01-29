package com.pomanagement.purchaseordermanagement.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Employee name is required")
    @Size(min = 3, max = 50, message = "Name must be 3–50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
