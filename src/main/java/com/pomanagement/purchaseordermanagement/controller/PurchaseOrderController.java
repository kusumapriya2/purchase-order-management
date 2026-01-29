package com.pomanagement.purchaseordermanagement.controller;
import com.pomanagement.purchaseordermanagement.dto.PurchaseOrderDTO;
import com.pomanagement.purchaseordermanagement.entity.PurchaseOrder;
import com.pomanagement.purchaseordermanagement.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    @PostMapping("/create")
    public ResponseEntity<PurchaseOrder> create(@RequestBody @Valid PurchaseOrderDTO dto) {
        return new ResponseEntity<>(service.createPO(dto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PurchaseOrder> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPO(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrder>> getAll() {
        return ResponseEntity.ok(service.getAllPOs());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PurchaseOrder> update(
            @PathVariable Long id,@Valid
            @RequestBody PurchaseOrderDTO dto) {
        return ResponseEntity.ok(service.updatePO(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deletePO(id);
        return ResponseEntity.ok("PO deleted successfully");
    }
}
