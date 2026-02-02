package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import com.pomanagement.purchaseordermanagement.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/payments")
public class PaymentDetailsController {

    @Autowired
    private PaymentDetailsService service;

    // CREATE PAYMENT
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PaymentDetailsDTO>> create(
            @RequestBody @Valid PaymentDetailsDTO dto) {

        return service.createPayment(dto);
    }

    // GET PAYMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentDetailsDTO>> get(@PathVariable Long id) {
        return service.getPayment(id);
    }
}
