package com.pomanagement.purchaseordermanagement.mapper;
import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(@Valid Employee dto);
}

