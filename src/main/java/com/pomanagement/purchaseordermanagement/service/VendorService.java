package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import com.pomanagement.purchaseordermanagement.mapper.VendorMapper;
import com.pomanagement.purchaseordermanagement.repository.VendorRepo;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private VendorMapper vendorMapper;


    // CREATE VENDOR
    public ResponseEntity<ApiResponse<VendorDTO>> createVendor(VendorDTO dto) {
        Vendor saved = vendorRepo.save(vendorMapper.toEntity(dto));
        return ResponseEntity.status(201)
                .body(ApiResponse.success("Vendor created successfully", vendorMapper.toDto(saved)));
    }

    // GET ALL VENDORS
    public ResponseEntity<ApiResponse<List<VendorDTO>>> getAllVendors() {
        List<VendorDTO> dtos = vendorRepo.findAll()
                .stream()
                .map(vendorMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("All vendors fetched successfully", dtos));
    }

    // GET BY ID
    public ResponseEntity<ApiResponse<VendorDTO>> getVendorById(Long id) {
        return vendorRepo.findById(id)
                .map(vendor -> ResponseEntity.ok(
                        ApiResponse.success("Vendor fetched successfully", vendorMapper.toDto(vendor))
                ))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(ApiResponse.failure("Vendor not found")));
    }

    // UPDATE VENDOR (manual simple update)
    public ResponseEntity<ApiResponse<VendorDTO>> updateVendor(Long id, VendorDTO dto) {
        Optional<Vendor> vendorOpt = vendorRepo.findById(id);

        if (vendorOpt.isPresent()) {
            Vendor vendor = vendorOpt.get();
            // Simple manual update
            vendor.setName(dto.getName());
            vendor.setEmail(dto.getEmail());
            Vendor updated = vendorRepo.save(vendor);
            return ResponseEntity.ok(ApiResponse.success("Vendor updated successfully", vendorMapper.toDto(updated)));
        }

        return ResponseEntity.status(404).body(ApiResponse.failure("Vendor not found"));
    }

    // DELETE
    public ResponseEntity<ApiResponse<String>> deleteVendor(Long id) {
        Optional<Vendor> vendorOpt = vendorRepo.findById(id);

        if (vendorOpt.isPresent()) {
            vendorRepo.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Vendor deleted successfully", null));
        }

        return ResponseEntity.status(404).body(ApiResponse.failure("Vendor not found"));
    }
}
