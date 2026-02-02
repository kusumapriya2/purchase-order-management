package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import com.pomanagement.purchaseordermanagement.mapper.PaymentDetailsMapper;
import com.pomanagement.purchaseordermanagement.repository.PaymentDetailsRepo;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<ApiResponse<PaymentDetailsDTO>> createPayment(PaymentDetailsDTO dto) {
        try {
            PaymentDetails payment = mapper.toEntity(dto); // map dto -> entity
            PaymentDetails saved = repo.save(payment);
            PaymentDetailsDTO responseDto = mapper.toDTO(saved);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Payment created successfully", responseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to create payment", null));
        }
    }
    // GET PAYMENT BY ID
    public ResponseEntity<ApiResponse<PaymentDetailsDTO>> getPayment(Long id) {
        log.info("Fetching PaymentDetails with id {}", id);

        Optional<PaymentDetails> found = repo.findById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Payment fetched successfully",
                            mapper.toDTO(found.get())
                    )
            );
        }

        log.warn("PaymentDetails not found with id {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(
                        false,
                        "PaymentDetails not found",
                        null
                )
        );
    }
}
