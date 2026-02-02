package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import com.pomanagement.purchaseordermanagement.mapper.PaymentDetailsMapper;
import com.pomanagement.purchaseordermanagement.repository.PaymentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepo repo;

    @Autowired
    private PaymentDetailsMapper mapper;

    public PaymentDetailsDTO createPayment(PaymentDetailsDTO dto) {
        PaymentDetails payment = mapper.toEntity(dto);
        PaymentDetails saved = repo.save(payment);
        return mapper.toDTO(saved);
    }

    public PaymentDetailsDTO getPayment(Long id) {
        PaymentDetails payment = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return mapper.toDTO(payment);
    }
}
