package com.pomanagement.purchaseordermanagement.mapper;
import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(@Valid Employee dto);

    @Mapping(target = "id", ignore = true) // don't overwrite ID
    void updateEmployeeFromDto(EmployeeDTO dto, @MappingTarget Employee employee);
}

