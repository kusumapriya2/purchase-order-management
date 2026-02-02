package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.entity.Product;
import org.mapstruct.Mapper;


import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);

    List<ProductDTO> toDtoList(List<Product> entities);


}
