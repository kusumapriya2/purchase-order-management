package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import com.pomanagement.purchaseordermanagement.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<VendorDTO>> createVendor(
            @RequestBody @Valid VendorDTO dto) {
        return vendorService.createVendor(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VendorDTO>>> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VendorDTO>> getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VendorDTO>> updateVendor(
            @PathVariable Long id,
            @RequestBody @Valid VendorDTO dto) {
        return vendorService.updateVendor(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVendor(@PathVariable Long id) {
        return vendorService.deleteVendor(id);
    }
}
