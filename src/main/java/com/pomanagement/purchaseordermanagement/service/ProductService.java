package com.pomanagement.purchaseordermanagement.service;

import com.pomanagement.purchaseordermanagement.dto.ProductDTO;
import com.pomanagement.purchaseordermanagement.entity.Product;
import com.pomanagement.purchaseordermanagement.mapper.ProductMapper;
import com.pomanagement.purchaseordermanagement.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired ProductRepo productRepo;
    @Autowired ProductMapper productMapper;

    public ResponseEntity<ProductDTO> createProduct(ProductDTO dto){
        try {
            log.info("product creating {}",dto.getName());
            Product product=productMapper.toEntity(dto);
            Product saved=productRepo.save(product);
            return ResponseEntity.ok(productMapper.toDTO(saved));
        } catch (Exception e) {
            log.error("error while creating product",e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<ProductDTO>> getAll(){
        try{
            List<Product> products = productRepo.findAll();
            return ResponseEntity.ok(productMapper.toDtoList(products));
        } catch (Exception e) {
            log.error("error while fetching products",e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<ProductDTO> getById(Long id){
        try{
            Product product=productRepo.findById(id)
                    .orElseThrow(()-> new RuntimeException("product not found"));
            return ResponseEntity.ok(productMapper.toDTO(product));
        } catch (Exception e) {
            log.error("error while fetching product",e);
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO dto) {
        try {
            log.info("Updating product with id {}", id);
            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            productMapper.updateProductFromDto(dto, product);
            Product updated = productRepo.save(product);

            return ResponseEntity.ok(productMapper.toDTO(updated));
        } catch (Exception e) {
            log.error("Failed to update product with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
        public ResponseEntity<String> deleteProduct(Long id) {
            try {
                log.info("Deleting product with id {}", id);
                productRepo.deleteById(id);
                return ResponseEntity.ok("deleted successfully");
            } catch (Exception e) {
                log.error("Error deleting product with id {}", id, e);
                return ResponseEntity.internalServerError().build();
            }
    }
}
