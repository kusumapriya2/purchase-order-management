package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO toDto(Vendor vendor);
    Vendor toEntity(VendorDTO dto);
    List<VendorDTO> toDtoList(List<Vendor> vendors);

}
