package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import com.pomanagement.purchaseordermanagement.mapper.PaymentDetailsMapper;
import com.pomanagement.purchaseordermanagement.repository.PaymentDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepo repo;

    @Autowired
    private PaymentDetailsMapper mapper;

    // CREATE PAYMENT
    public ResponseEntity<PaymentDetailsDTO> createPayment(PaymentDetailsDTO dto) {
        try {
            log.info("Creating payment for method: {}", dto.getPaymentMethod());
            PaymentDetails payment = mapper.toEntity(dto);
            PaymentDetails saved = repo.save(payment);
            return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while creating payment", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET PAYMENT BY ID
    public ResponseEntity<PaymentDetailsDTO> getPayment(Long id) {
        log.info("Fetching payment with id {}", id);
        Optional<PaymentDetails> optionalPayment = repo.findById(id);

        return optionalPayment
                .map(payment -> new ResponseEntity<>(mapper.toDTO(payment), HttpStatus.OK))
                .orElseGet(() -> {
                    log.warn("Payment not found with id {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
