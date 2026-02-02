package com.pomanagement.purchaseordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private java.util.List<PurchaseOrder> purchaseOrders;
}
