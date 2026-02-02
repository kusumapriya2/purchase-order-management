package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.mapper.EmployeeMapper;
import com.pomanagement.purchaseordermanagement.repository.EmployeeRepo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    // CREATE
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid Employee dto) {
        try {
            log.info("Creating employee {}", dto.getName());
            Employee employee = employeeMapper.toEntity(dto);
            Employee saved = employeeRepo.save(employee);
            return new ResponseEntity<>(employeeMapper.toDto(saved), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while creating employee", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        try {
            log.info("Fetching all employees");
            List<EmployeeDTO> dtos = employeeRepo.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching employees", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID
    public ResponseEntity<EmployeeDTO> getEmp(Long id) {
        log.info("Fetching employee with id {}", id);
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        return optionalEmployee
                .map(employee -> new ResponseEntity<>(employeeMapper.toDto(employee), HttpStatus.OK))
                .orElseGet(() -> {
                    log.warn("Employee not found with id {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    // UPDATE
    public ResponseEntity<EmployeeDTO> updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Updating employee with id {}", id);
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeMapper.updateEmployeeFromDto(dto, employee);
            Employee updated = employeeRepo.save(employee);
            return new ResponseEntity<>(employeeMapper.toDto(updated), HttpStatus.OK);
        } else {
            log.warn("Employee not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    public ResponseEntity<String> deleteEmp(Long id) {
        log.info("Deleting employee with id {}", id);
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if(optionalEmployee.isPresent()) {
            employeeRepo.deleteById(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            log.warn("Employee not found with id {}", id);
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}
