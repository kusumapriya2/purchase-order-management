package com.pomanagement.purchaseordermanagement.repository;

import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
}
