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
@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    @Mapping(source = "employeeId", target = "employee", qualifiedByName = "employeeById")
    @Mapping(source = "vendorId", target = "vendor", qualifiedByName = "vendorById")
    @Mapping(source = "productIds", target = "products", qualifiedByName = "productsByIds")
    PurchaseOrder toEntity(
            PurchaseOrderDTO dto,
            @Context EmployeeRepo employeeRepo,
            @Context VendorRepo vendorRepo,
            @Context ProductRepo productRepo
    );

    @Named("employeeById")
    default Employee mapEmployee(Long id, @Context EmployeeRepo repo) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Named("vendorById")
    default Vendor mapVendor(Long id, @Context VendorRepo repo) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @Named("productsByIds")
    default List<Product> mapProducts(
            List<Long> ids,
            @Context ProductRepo repo
    ) {
        return repo.findAllById(ids);
    }

    PurchaseOrderDTO toDTO(PurchaseOrder saved);
}
