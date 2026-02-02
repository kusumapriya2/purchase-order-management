package com.pomanagement.purchaseordermanagement.mapper;

import com.pomanagement.purchaseordermanagement.dto.PaymentDetailsDTO;
import com.pomanagement.purchaseordermanagement.entity.PaymentDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentDetailsMapper {

    PaymentDetailsMapper INSTANCE = Mappers.getMapper(PaymentDetailsMapper.class);

    PaymentDetailsDTO toDTO(PaymentDetails paymentDetails);

    PaymentDetails toEntity(PaymentDetailsDTO paymentDetailsDTO);
}
