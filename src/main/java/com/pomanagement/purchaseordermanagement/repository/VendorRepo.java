package com.pomanagement.purchaseordermanagement.repository;

import com.pomanagement.purchaseordermanagement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
}
