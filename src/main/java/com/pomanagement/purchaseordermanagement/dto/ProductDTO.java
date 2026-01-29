package com.pomanagement.purchaseordermanagement.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;
}
