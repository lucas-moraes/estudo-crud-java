package com.example.crud.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductDTO;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAllByActiveTrue();
    }

    public Product registerProduct(RequestProductDTO product) {
        Product newProduct = new Product(product);
        repository.save(newProduct);
        return newProduct;
    }

    public Boolean updateProduct(RequestProductDTO product) {
        Optional<Product> optionalProduct = repository.findById(product.id());
        if (optionalProduct.isPresent()) {
            Product dataProduct = optionalProduct.get();
            dataProduct.setName(product.name());
            dataProduct.setPrice_in_cents(product.price_in_cents());
            return true;
        }
        return false;
    }

    public Boolean deleteProduct(String id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return true;
        }
        return false;
    }
}
