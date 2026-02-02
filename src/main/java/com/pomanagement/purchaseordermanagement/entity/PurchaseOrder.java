package com.pomanagement.purchaseordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many POs can be created by One Employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Many POs can be supplied by One Vendor
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Many POs can have many Products
    @ManyToMany
    @JoinTable(
            name = "po_products",
            joinColumns = @JoinColumn(name = "po_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String status;

    // Each PO can have one PaymentDetails
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentDetails paymentDetails;
}
