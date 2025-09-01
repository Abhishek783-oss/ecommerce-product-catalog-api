package com.ecommerce.catalog.productcatalog.service;

import com.ecommerce.catalog.productcatalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    List<Product> searchByName(String name);
    Product update(String id, Product product);
    void delete(String id);
    Page<Product> getAllPaged(Pageable pageable);
    Page<Product> filterProducts(String category, String brand, BigDecimal minPrice, BigDecimal maxPrice, Double minRating, Pageable pageable);
}
