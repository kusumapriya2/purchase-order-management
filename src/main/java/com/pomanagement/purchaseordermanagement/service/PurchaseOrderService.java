package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.mapper.PurchaseOrderMapper;
import com.pomanagement.purchaseordermanagement.repository.EmployeeRepo;
import com.pomanagement.purchaseordermanagement.repository.ProductRepo;
import com.pomanagement.purchaseordermanagement.repository.PurchaseOrderRepo;
import com.pomanagement.purchaseordermanagement.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
@Service
@Slf4j
public class PurchaseOrderService {

    @Autowired private PurchaseOrderRepo poRepo;
    @Autowired private PurchaseOrderMapper mapper;
    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private VendorRepo vendorRepo;
    @Autowired private ProductRepo productRepo;


    public PurchaseOrder createPO(PurchaseOrderDTO dto) {
        try {
            log.info("Creating Purchase Order");

            PurchaseOrder po = mapper.toEntity(
                    dto, employeeRepo, vendorRepo, productRepo
            );
            po.setStatus("CREATED");

            return poRepo.save(po);

        } catch (Exception e) {
            log.error("Error creating PO", e);
            throw new RuntimeException("PO creation failed");
        }
    }

    public PurchaseOrder getPO(Long id) {
        try {
            return poRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("PO not found"));
        } catch (Exception e) {
            log.error("Error fetching PO {}", id, e);
            throw e;
        }
    }


    public List<PurchaseOrder> getAllPOs() {
        return poRepo.findAll();
    }


    public PurchaseOrder updatePO(Long id, PurchaseOrderDTO dto) {
        try {
            PurchaseOrder existing = getPO(id);

            PurchaseOrder updated = mapper.toEntity(
                    dto, employeeRepo, vendorRepo, productRepo
            );
            updated.setId(existing.getId());

            return poRepo.save(updated);

        } catch (Exception e) {
            log.error("Error updating PO {}", id, e);
            throw new RuntimeException("Update failed");
        }
    }


    public void deletePO(Long id) {
        try {
            poRepo.deleteById(id);
            log.info("PO deleted: {}", id);
        } catch (Exception e) {
            log.error("Delete failed {}", id, e);
            throw new RuntimeException("Delete failed");
        }
    }
}
