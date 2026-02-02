package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import com.pomanagement.purchaseordermanagement.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> create(@RequestBody @Valid PurchaseOrderDTO dto) {
       return service.createPO(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> get(@PathVariable Long id) {
        return service.getPO(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAll() {
        return service.getAllPOs(); // service already returns ResponseEntity<ApiResponse<?>>
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PurchaseOrderDTO>> update(
            @PathVariable Long id,
            @RequestBody @Valid PurchaseOrderDTO dto){
        return service.updatePO(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        return service.deletePO(id);
    }
}
