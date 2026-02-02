package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
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
    public PurchaseOrder create(@RequestBody @Valid PurchaseOrderDTO dto) {
        return service.createPO(dto).getBody();
    }

    @GetMapping("/{id}")
    public PurchaseOrder get(@PathVariable Long id) {
        return service.getPO(id).getBody();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrder>> getAll() {
        return (ResponseEntity<List<PurchaseOrder>>) service.getAllPOs();
    }

    @PutMapping("/{id}")
    public PurchaseOrder update(
            @PathVariable Long id,
            @RequestBody @Valid PurchaseOrderDTO dto
    ) {
        return service.updatePO(id, dto).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.deletePO(id);
    }
}
