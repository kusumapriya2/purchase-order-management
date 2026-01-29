package com.pomanagement.purchaseordermanagement.controller;
import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping("/create")
    public ResponseEntity<VendorDTO> createVendor(@RequestBody @Valid VendorDTO dto) {
        return vendorService.createVendor(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id,@Valid
                                              @RequestBody VendorDTO dto) {
        return vendorService.updateVendor(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        return vendorService.deleteVendor(id);
    }
}

