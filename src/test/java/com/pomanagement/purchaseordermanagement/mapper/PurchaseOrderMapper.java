package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PurchaseOrderMapper {
    PurchaseOrder toEntity(PurchaseOrderDTO dto);
}
