package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.VendorDTO;
import com.pomanagement.purchaseordermanagement.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    // Entity -> DTO
    VendorDTO toDto(Vendor vendor);

    // DTO -> Entity
    Vendor toEntity(VendorDTO dto);

    // List<Entity> -> List<DTO>
    List<VendorDTO> toDtoList(List<Vendor> vendors);

    // Update existing entity with DTO values
    void updateVendorFromDto(VendorDTO dto, @MappingTarget Vendor vendor);
}
