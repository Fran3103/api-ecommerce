package com.Ecomerce.product_service.service;

import com.Ecomerce.product_service.dtos.ProductDto;
import com.Ecomerce.product_service.entity.Product;
import com.Ecomerce.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepo;


    @Override
    public Product save(Product productDto) {
        return productRepo.save(productDto);
    }

    @Override
    public Product update(Product productDto) {
        return productRepo.save(productDto);
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product findByCode(String code) {
        return productRepo.findByCode(code);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        return productRepo.findAllById(ids);
    }
}
