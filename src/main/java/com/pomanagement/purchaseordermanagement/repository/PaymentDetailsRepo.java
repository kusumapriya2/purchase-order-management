package com.pomanagement.purchaseordermanagement.repository;

import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, Long> {
}
