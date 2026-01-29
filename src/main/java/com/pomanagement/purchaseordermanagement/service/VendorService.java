package com.pomanagement.purchaseordermanagement.service;
import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import com.pomanagement.purchaseordermanagement.mapper.VendorMapper;
import com.pomanagement.purchaseordermanagement.repository.VendorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class VendorService {
    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private VendorMapper vendorMapper;
    public ResponseEntity<VendorDTO> createVendor(VendorDTO dto) {
        try {
            log.info("Creating vendor {}", dto.getName());
            Vendor vendor = vendorMapper.toEntity(dto); // <-- FIXED
            Vendor saved = vendorRepo.save(vendor);
            return ResponseEntity.ok(vendorMapper.toDto(saved));
        } catch (Exception e) {
            log.error("Error creating vendor", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        try {
            List<Vendor> vendors = vendorRepo.findAll();
            return ResponseEntity.ok(vendorMapper.toDtoList(vendors));
        } catch (Exception e) {
            log.error("Error fetching vendors", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<VendorDTO> getVendorById(Long id) {
        try {
            Vendor vendor = vendorRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));
            return ResponseEntity.ok(vendorMapper.toDto(vendor));
        } catch (Exception e) {
            log.error("Error fetching vendor with id {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<VendorDTO> updateVendor(Long id, VendorDTO dto) {
        try {
            log.info("Updating vendor with id {}", id);
            Vendor vendor = vendorRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));

            vendorMapper.updateVendorFromDto(dto, vendor);
            Vendor updated = vendorRepo.save(vendor);

            return ResponseEntity.ok(vendorMapper.toDto(updated));
        } catch (Exception e) {
            log.error("Failed to update vendor with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<Void> deleteVendor(Long id) {
        try {
            log.info("Deleting vendor with id {}", id);
            vendorRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting vendor with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}