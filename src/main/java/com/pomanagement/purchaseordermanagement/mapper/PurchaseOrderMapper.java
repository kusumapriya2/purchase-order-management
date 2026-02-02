package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.entity.Product;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import com.pomanagement.purchaseordermanagement.repository.EmployeeRepo;
import com.pomanagement.purchaseordermanagement.repository.ProductRepo;
import com.pomanagement.purchaseordermanagement.repository.VendorRepo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "vendorId", source = "vendor.id")
    @Mapping(target = "paymentDetailsId", source = "paymentDetails.id")
    @Mapping(target = "productIds", expression = "java(po.getProducts().stream().map(p -> p.getId()).collect(java.util.stream.Collectors.toList()))")
    PurchaseOrderDTO toDTO(PurchaseOrder po);
    PurchaseOrder toEntity(PurchaseOrderDTO dto, EmployeeRepo employeeRepo, VendorRepo vendorRepo, ProductRepo productRepo);
}
