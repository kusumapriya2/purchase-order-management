package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentDetailsController {

    @Autowired
    private PaymentDetailsService service;

    @PostMapping("/create")
    public PaymentDetailsDTO create(@RequestBody PaymentDetailsDTO dto) {
        return service.createPayment(dto);
    }

    @GetMapping("/{id}")
    public PaymentDetailsDTO get(@PathVariable Long id) {
        return service.getPayment(id);
    }
}
