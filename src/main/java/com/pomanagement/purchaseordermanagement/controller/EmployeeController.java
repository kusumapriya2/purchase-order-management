package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.EmployeeDTO;
import com.pomanagement.purchaseordermanagement.entity.Employee;
import com.pomanagement.purchaseordermanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmp(@RequestBody @Valid Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmp(id));
    }
    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmp(
            @PathVariable Long id,@Valid
            @RequestBody EmployeeDTO dto
    ) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id){
        employeeService.deleteEmp(id);
        return ResponseEntity.ok("employee delete successfully");
    }
}
