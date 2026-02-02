package com.pomanagement.purchaseordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    // Many Products can be in Many Purchase Orders
    @ManyToMany(mappedBy = "products")
    @JsonIgnore // prevents infinite recursion in JSON serialization
    private List<PurchaseOrder> purchaseOrders;
}
