package com.ecommerce.catalog.productcatalog.service;

import com.ecommerce.catalog.productcatalog.model.Product;
import com.ecommerce.catalog.productcatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product create(Product product) {
        return repo.save(product);
    }

    @Override
    public Product getById(String id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product update(String id, Product product) {
        Product existing = getById(id);
        existing.setName(product.getName());
        existing.setBrand(product.getBrand());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());
        existing.setCategory(product.getCategory());
        existing.setRating(product.getRating());
        return repo.save(existing);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Page<Product> getAllPaged(Pageable pageable) {
        return repo.findAll(pageable);
    }
    @Override
    public Page<Product> filterProducts(String category, String brand, BigDecimal minPrice, BigDecimal maxPrice, Double minRating, Pageable pageable){
        return repo.filterProducts(category,brand, minPrice, maxPrice, minRating, pageable);
    }
}
