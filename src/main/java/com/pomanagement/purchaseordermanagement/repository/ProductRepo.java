package com.pomanagement.purchaseordermanagement.repository;

import com.pomanagement.purchaseordermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
