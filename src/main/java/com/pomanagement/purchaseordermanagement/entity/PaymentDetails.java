package com.pomanagement.purchaseordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod; // UPI, CARD, CASH
    private String transactionId;
}

