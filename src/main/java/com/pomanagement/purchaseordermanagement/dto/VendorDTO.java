package com.pomanagement.purchaseordermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendorDTO {

    private Long id;

    @NotBlank(message = "Vendor name is required")
    private String name;

    @NotBlank(message = "Vendor email is required")
    @Email(message = "Invalid vendor email")
    private String email;
}
