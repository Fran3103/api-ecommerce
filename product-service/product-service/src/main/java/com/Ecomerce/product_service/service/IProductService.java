package com.Ecomerce.product_service.service;

import com.Ecomerce.product_service.dtos.ProductDto;
import com.Ecomerce.product_service.entity.Product;

import java.util.List;


public interface IProductService {

    Product save(Product productDto);
    Product update(Product productDto);
    Product findById(Long id);
    Product findByCode(String code);
    void delete(Long id);
    List<Product> findAll();

    List<Product> findByIds(List<Long> ids);
}
