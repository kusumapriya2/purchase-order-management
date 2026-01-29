package com.pomanagement.purchaseordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
}


