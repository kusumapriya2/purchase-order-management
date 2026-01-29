package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.mapper.EmployeeMapper;
import com.pomanagement.purchaseordermanagement.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee createEmployee(Employee employee) {
        try {
            log.info("Creating employee {}", employee.getName());
            return employeeRepo.save(employee);
        } catch (Exception e) {
            log.error("Error while creating employee", e);
            throw new RuntimeException("Employee creation failed");
        }
    }

    public List<Employee> getAll() {
        try {
            log.info("Fetching all employees");
            return employeeRepo.findAll();
        } catch (Exception e) {
            log.error("Error while fetching employees", e);
            throw new RuntimeException("Failed to fetch employees");
        }
    }

    public Employee getEmp(Long id) {
        try {
            log.info("Fetching employee with id {}", id);
            return employeeRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        } catch (Exception e) {
            log.error("Error while fetching employee with id {}", id, e);
            throw e;
        }
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Updating employee with id {}", id);
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeMapper.updateEmployeeFromDto(dto, employee);
        Employee updated = employeeRepo.save(employee);
        return employeeMapper.toDto(updated);
    }


    public void deleteEmp(Long id) {
        try {
            log.info("Deleting employee with id {}", id);
            employeeRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Error while deleting employee", e);
            throw new RuntimeException("Failed to delete employee");
        }
    }
}
