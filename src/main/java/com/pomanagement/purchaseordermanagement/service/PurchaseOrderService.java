package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.mapper.PurchaseOrderMapper;
import com.pomanagement.purchaseordermanagement.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PurchaseOrderService {

    @Autowired private PurchaseOrderRepo poRepo;
    @Autowired private PurchaseOrderMapper mapper;
    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private VendorRepo vendorRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private PaymentDetailsRepo paymentDetailsRepo;

    // CREATE
    public ResponseEntity<PurchaseOrder> createPO(PurchaseOrderDTO dto) {
        try {
            log.info("Creating Purchase Order");

            PurchaseOrder po = mapper.toEntity(dto, employeeRepo, vendorRepo, productRepo);

            if (dto.getPaymentDetailsId() != null) {
                PaymentDetails pd = paymentDetailsRepo.findById(dto.getPaymentDetailsId())
                        .orElseThrow(() -> new RuntimeException("PaymentDetails not found"));
                po.setPaymentDetails(pd);
            }

            po.setStatus("CREATED");
            PurchaseOrder saved = poRepo.save(po);

            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            log.error("Error creating PO", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // READ BY ID
    public ResponseEntity<PurchaseOrder> getPO(Long id) {
        Optional<PurchaseOrder> po = poRepo.findById(id);
        return po.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    // READ ALL
    public ResponseEntity<List<PurchaseOrder>> getAllPOs() {
        List<PurchaseOrder> list = poRepo.findAll();
        return ResponseEntity.ok(list);
    }

    // UPDATE
    public ResponseEntity<PurchaseOrder> updatePO(Long id, PurchaseOrderDTO dto) {
        Optional<PurchaseOrder> existingOpt = poRepo.findById(id);

        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        try {
            PurchaseOrder updated = mapper.toEntity(dto, employeeRepo, vendorRepo, productRepo);
            updated.setId(existingOpt.get().getId());

            if (dto.getPaymentDetailsId() != null) {
                PaymentDetails pd = paymentDetailsRepo.findById(dto.getPaymentDetailsId())
                        .orElseThrow(() -> new RuntimeException("PaymentDetails not found"));
                updated.setPaymentDetails(pd);
            }

            PurchaseOrder saved = poRepo.save(updated);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error updating PO {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE
    public ResponseEntity<String> deletePO(Long id) {
        Optional<PurchaseOrder> existingOpt = poRepo.findById(id);

        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(404).body("PO not found");
        }

        try {
            poRepo.deleteById(id);
            return ResponseEntity.ok("PO deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting PO {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
