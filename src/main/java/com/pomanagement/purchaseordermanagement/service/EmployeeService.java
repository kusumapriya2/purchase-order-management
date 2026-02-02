package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.mapper.EmployeeMapper;
import com.pomanagement.purchaseordermanagement.repository.EmployeeRepo;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMapper employeeMapper;


    // CREATE EMPLOYEE
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@Valid Employee dto) {
        try {
            log.info("Creating employee: {}", dto.getName());

            Employee saved = employeeRepo.save(employeeMapper.toEntity(dto));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Employee created successfully",
                            employeeMapper.toDto(saved)));

        } catch (Exception e) {
            log.error("Error creating employee", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Failed to create employee"));
        }
    }


    // GET ALL EMPLOYEES
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAll() {
        try {
            log.info("Fetching all employees");

            List<EmployeeDTO> dtos = employeeRepo.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                    ApiResponse.success("All employees fetched successfully", dtos)
            );

        } catch (Exception e) {
            log.error("Error fetching employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Failed to fetch employees"));
        }
    }


    // GET EMPLOYEE BY ID
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmp(Long id) {
        Optional<Employee> found = employeeRepo.findById(id);

        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("Employee not found"));
        }

        return ResponseEntity.ok(
                ApiResponse.success("Employee found",
                        employeeMapper.toDto(found.get()))
        );
    }


    // UPDATE EMPLOYEE (SIMPLE VERSION — NO MAPSTRUCT UPDATE)
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(Long id, EmployeeDTO dto) {

        Optional<Employee> found = employeeRepo.findById(id);

        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("Employee not found"));
        }

        Employee employee = found.get();

        // SIMPLE MANUAL UPDATE
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());

        Employee updated = employeeRepo.save(employee);

        return ResponseEntity.ok(
                ApiResponse.success("Employee updated successfully",
                        employeeMapper.toDto(updated))
        );
    }


    // DELETE EMPLOYEE
    public ResponseEntity<ApiResponse<Object>> deleteEmp(Long id) {
        Optional<Employee> found = employeeRepo.findById(id);

        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("Employee not found"));
        }

        employeeRepo.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.success("Employee deleted successfully", null)
        );
    }
}
