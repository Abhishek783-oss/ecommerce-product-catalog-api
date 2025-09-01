package com.ecommerce.catalog.productcatalog.repository;

import com.ecommerce.catalog.productcatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
