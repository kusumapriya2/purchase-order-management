package com.pomanagement.purchaseordermanagement.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class PurchaseOrderDTO {

    private Long id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotEmpty(message = "At least one product is required")
    private List<Long> productIds;
    private String status;

    private Long paymentDetailsId;

}
