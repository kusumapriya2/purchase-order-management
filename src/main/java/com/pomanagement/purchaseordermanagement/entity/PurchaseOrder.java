package com.pomanagement.purchaseordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne

    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne

    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "po_products",
            joinColumns = @JoinColumn(name = "po_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentDetails paymentDetails;

}
