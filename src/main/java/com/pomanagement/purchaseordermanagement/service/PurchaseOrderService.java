package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.mapper.PurchaseOrderMapper;
import com.pomanagement.purchaseordermanagement.repository.*;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PurchaseOrderService {

    @Autowired private PurchaseOrderRepo poRepo;
    @Autowired private PurchaseOrderMapper mapper;
    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private VendorRepo vendorRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private PaymentDetailsRepo paymentDetailsRepo;


    // CREATE PO
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> createPO(PurchaseOrderDTO dto) {
        try {
            log.info("Creating Purchase Order");

            PurchaseOrder po = mapper.toEntity(dto, employeeRepo, vendorRepo, productRepo);

            if (dto.getPaymentDetailsId() != null) {
                PaymentDetails pd = paymentDetailsRepo.findById(dto.getPaymentDetailsId())
                        .orElseThrow(() -> new RuntimeException("Payment Details not found"));
                po.setPaymentDetails(pd);
            }

            po.setStatus("CREATED");
            PurchaseOrder saved = poRepo.save(po);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(
                            true,
                            "Purchase Order created successfully",
                            mapper.toDTO(saved)
                    )
            );

        } catch (Exception e) {
            log.error("Error creating PO", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(
                            false,
                            "Failed to create Purchase Order: " + e.getMessage(),
                            null
                    )
            );
        }
    }


    // GET PO BY ID
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> getPO(Long id) {
        log.info("Fetching PO with id {}", id);

        Optional<PurchaseOrder> po = poRepo.findById(id);

        if (po.isPresent()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Purchase Order fetched successfully",
                            mapper.toDTO(po.get())
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(
                        false,
                        "Purchase Order not found",
                        null
                )
        );
    }


    // GET ALL POs
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAllPOs() {
        log.info("Fetching all purchase orders");

        List<PurchaseOrderDTO> list = poRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Purchase Orders fetched successfully",
                        list
                )
        );
    }


    // UPDATE PO
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> updatePO(Long id, @Valid PurchaseOrderDTO dto) {
        log.info("Updating PO with id {}", id);

        Optional<PurchaseOrder> existingOpt = poRepo.findById(id);

        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            "Purchase Order not found",
                            null
                    )
            );
        }

        try {
            PurchaseOrder updated = mapper.toEntity(dto, employeeRepo, vendorRepo, productRepo);
            updated.setId(existingOpt.get().getId());

            if (dto.getPaymentDetailsId() != null) {
                PaymentDetails pd = paymentDetailsRepo.findById(dto.getPaymentDetailsId())
                        .orElseThrow(() -> new RuntimeException("Payment Details not found"));
                updated.setPaymentDetails(pd);
            }

            PurchaseOrder saved = poRepo.save(updated);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Purchase Order updated successfully",
                            mapper.toDTO(saved)
                    )
            );

        } catch (Exception e) {
            log.error("Error updating PO {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(
                            false,
                            "Failed to update Purchase Order",
                            null
                    )
            );
        }
    }


    // DELETE PO
    public ResponseEntity<ApiResponse<String>> deletePO(Long id) {
        log.info("Deleting PO with id {}", id);

        Optional<PurchaseOrder> existingOpt = poRepo.findById(id);

        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            "Purchase Order not found",
                            null
                    )
            );
        }

        try {
            poRepo.deleteById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Purchase Order deleted successfully",
                            "Deleted"
                    )
            );

        } catch (Exception e) {
            log.error("Error deleting PO {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(
                            false,
                            "Failed to delete Purchase Order",
                            null
                    )
            );
        }
    }
}
