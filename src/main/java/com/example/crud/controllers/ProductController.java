package com.example.crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.RequestProductDTO;
import com.example.crud.services.product.ProductService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid RequestProductDTO data) {
        Product response = productService.registerProduct(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProductDTO data) {
        var response = productService.updateProduct(data);
        if (response) {
            return ResponseEntity.ok().build();
        }
        throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        var response = productService.deleteProduct(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        throw new EntityNotFoundException();
    }

}
