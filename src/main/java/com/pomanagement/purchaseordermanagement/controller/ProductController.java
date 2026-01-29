package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@Valid
                                                    @RequestBody ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}

