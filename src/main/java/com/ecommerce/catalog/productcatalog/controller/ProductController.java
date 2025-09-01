package com.ecommerce.catalog.productcatalog.controller;

import com.ecommerce.catalog.productcatalog.model.Product;
import com.ecommerce.catalog.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }
    @GetMapping
    public Page<Product> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort
    ){
        Sort.Direction direction=Sort.Direction.fromString(sort[1]);
        Sort sortOrder =   Sort.by(direction,sort[0]);
        Pageable pageable= PageRequest.of(page,size,sortOrder);
        return productService.getAllPaged(pageable);
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable String id){
        return productService.getById(id);
    }
    @GetMapping("/search")
    public List<Product> getByName(@PathVariable String name){
        return productService.searchByName(name);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id,@RequestBody Product product){
        return productService.update(id,product);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }
    @GetMapping("/filter")
    public Page<Product> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required=false) String brand,
            @RequestParam(required=false) BigDecimal minPrice,
            @RequestParam(required=false) BigDecimal maxPrice,
            @RequestParam(required=false) Double minRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort
    ){
        Sort.Direction direction=Sort.Direction.fromString(sort[1]);
        Sort sortOrder = Sort.by(direction,sort[0]);
        Pageable pageable=PageRequest.of(page,size,sortOrder);
        return productService.filterProducts(category,brand,minPrice,maxPrice,minRating,pageable);
    }
}
