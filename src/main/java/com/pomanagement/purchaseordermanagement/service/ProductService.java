package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.entity.Product; // <-- Add this
import com.pomanagement.purchaseordermanagement.mapper.ProductMapper;
import com.pomanagement.purchaseordermanagement.repository.ProductRepo;
import com.pomanagement.purchaseordermanagement.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    // CREATE
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(ProductDTO dto) {
        Product saved = productRepo.save(productMapper.toEntity(dto));
        return ResponseEntity.ok(ApiResponse.success("Product created", productMapper.toDTO(saved)));
    }

    // GET ALL
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAll() {
        List<ProductDTO> list = productRepo.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Products fetched", list));
    }

    // GET BY ID
    public ResponseEntity<ApiResponse<ProductDTO>> getById(Long id) {
        return productRepo.findById(id)
                .map(product -> ResponseEntity.ok(ApiResponse.success("Product fetched", productMapper.toDTO(product))))
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.failure("Product not found")));
    }

    // DELETE
    public ResponseEntity<ApiResponse<Object>> deleteProduct(Long id) {
        return productRepo.findById(id)
                .map(product -> {
                    productRepo.delete(product);
                    return ResponseEntity.ok(ApiResponse.success("Product deleted", null));
                })
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.failure("Product not found")));
    }


    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(Long id, ProductDTO dto) {
        log.info("Updating product with id {}", id);
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty()) {
            log.warn("Product not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("Product not found"));
        }
        Product product = optionalProduct.get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Product updated = productRepo.save(product);
        return ResponseEntity.ok(
                ApiResponse.success("Product updated successfully", productMapper.toDTO(updated))
        );
    }

}
