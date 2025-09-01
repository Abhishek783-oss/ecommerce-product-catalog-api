package com.ecommerce.catalog.productcatalog.repository;

import com.ecommerce.catalog.productcatalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByNameContainingIgnoreCase(String name);
    @Query("SELECT p FROM Product p "+"WHERE (:category IS NULL OR LOWER(p.category)= LOWER(:category)) "+
    "AND (:brand IS NULL OR LOWER(p.brand)=LOWER(:brand)) "+
    "AND (:minPrice IS NULL OR p.price>=:minPrice) "+
    "AND (:maxPrice IS NULL OR p.price<=:maxPrice) "+
    "AND (:minRating IS NULL OR p.rating>=:minRating)")
    Page<Product> filterProducts(
            @Param("category") String category,
            @Param("brand") String brand,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("minRating") Double minRating,
            Pageable pageable
            );
}
