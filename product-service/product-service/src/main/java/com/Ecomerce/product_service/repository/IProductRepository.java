package com.Ecomerce.product_service.repository;

import com.Ecomerce.product_service.dtos.ProductDto;
import com.Ecomerce.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.code = :code")
    Product findByCode(String code);
}
