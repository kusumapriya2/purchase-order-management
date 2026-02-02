package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.entity.Product;
import com.pomanagement.purchaseordermanagement.mapper.ProductMapper;
import com.pomanagement.purchaseordermanagement.repository.ProductRepo;
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
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    // CREATE PRODUCT
    public ResponseEntity<ProductDTO> createProduct(ProductDTO dto) {
        try {
            log.info("Creating product {}", dto.getName());
            Product product = productMapper.toEntity(dto);
            Product saved = productRepo.save(product);
            return new ResponseEntity<>(productMapper.toDTO(saved), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while creating product", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL PRODUCTS
    public ResponseEntity<List<ProductDTO>> getAll() {
        try {
            log.info("Fetching all products");
            List<ProductDTO> dtos = productRepo.findAll()
                    .stream()
                    .map(productMapper::toDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching products", e);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET PRODUCT BY ID
    public ResponseEntity<ProductDTO> getById(Long id) {
        log.info("Fetching product with id {}", id);
        Optional<Product> optionalProduct = productRepo.findById(id);

        return optionalProduct
                .map(product -> new ResponseEntity<>(productMapper.toDTO(product), HttpStatus.OK))
                .orElseGet(() -> {
                    log.warn("Product not found with id {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    // UPDATE PRODUCT
    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO dto) {
        log.info("Updating product with id {}", id);
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productMapper.updateProductFromDto(dto, product);
            Product updated = productRepo.save(product);
            return new ResponseEntity<>(productMapper.toDTO(updated), HttpStatus.OK);
        } else {
            log.warn("Product not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE PRODUCT
    public ResponseEntity<String> deleteProduct(Long id) {
        log.info("Deleting product with id {}", id);
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()) {
            productRepo.deleteById(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            log.warn("Product not found with id {}", id);
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}
