package com.pomanagement.purchaseordermanagement.controller;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import com.pomanagement.purchaseordermanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {

        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
