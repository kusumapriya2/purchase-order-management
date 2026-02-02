package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import com.pomanagement.purchaseordermanagement.mapper.VendorMapper;
import com.pomanagement.purchaseordermanagement.repository.VendorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<VendorDTO> createVendor(VendorDTO dto) {
        try {
            log.info("Creating vendor {}", dto.getName());
            Vendor vendor = vendorMapper.toEntity(dto);
            Vendor saved = vendorRepo.save(vendor);
            return new ResponseEntity<>(vendorMapper.toDto(saved), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating vendor", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL VENDORS
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        try {
            List<VendorDTO> dtos = vendorRepo.findAll()
                    .stream()
                    .map(vendorMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching vendors", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET VENDOR BY ID
    public ResponseEntity<VendorDTO> getVendorById(Long id) {
        log.info("Fetching vendor with id {}", id);
        Optional<Vendor> vendorOpt = vendorRepo.findById(id);

        return vendorOpt
                .map(vendor -> new ResponseEntity<>(vendorMapper.toDto(vendor), HttpStatus.OK))
                .orElseGet(() -> {
                    log.warn("Vendor not found with id {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    // UPDATE VENDOR
    public ResponseEntity<VendorDTO> updateVendor(Long id, VendorDTO dto) {
        log.info("Updating vendor with id {}", id);
        Optional<Vendor> vendorOpt = vendorRepo.findById(id);

        if (vendorOpt.isPresent()) {
            try {
                Vendor vendor = vendorOpt.get();
                vendorMapper.updateVendorFromDto(dto, vendor);
                Vendor updated = vendorRepo.save(vendor);
                return new ResponseEntity<>(vendorMapper.toDto(updated), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Failed to update vendor with id {}", id, e);
                return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            log.warn("Vendor not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE VENDOR
    public ResponseEntity<String> deleteVendor(Long id) {
        log.info("Deleting vendor with id {}", id);
        Optional<Vendor> vendorOpt = vendorRepo.findById(id);

        if (vendorOpt.isPresent()) {
            try {
                vendorRepo.deleteById(id);
                return new ResponseEntity<>("Vendor deleted successfully", HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error deleting vendor with id {}", id, e);
                return new ResponseEntity<>("Failed to delete vendor", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            log.warn("Vendor not found with id {}", id);
            return new ResponseEntity<>("Vendor not found", HttpStatus.NOT_FOUND);
        }
    }
}
