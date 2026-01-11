package com.Ecomerce.shoppingcart_service.repository;

import com.Ecomerce.shoppingcart_service.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductApiFallback implements IProductApi{
    @Override
    public List<ProductDto> getProductsByIds(List<Long> ids) {
        return List.of();
    }
}
