package com.pomanagement.purchaseordermanagement.controller;
import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import com.pomanagement.purchaseordermanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmp(@RequestBody @Valid Employee employee) {
        return employeeService.createEmployee(employee);
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getById(@PathVariable Long id) {
        return employeeService.getEmp(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmp(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeDTO dto
    ) {
        return employeeService.updateEmployee(id, dto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteEmp(@PathVariable Long id) {
        return employeeService.deleteEmp(id);
    }
}
