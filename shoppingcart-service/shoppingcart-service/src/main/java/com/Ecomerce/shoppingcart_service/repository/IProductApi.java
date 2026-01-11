package com.Ecomerce.shoppingcart_service.repository;

import com.Ecomerce.shoppingcart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE", fallback = ProductApiFallback.class)
public interface IProductApi {

    @PostMapping("/products/ids")
    public List<ProductDto> getProductsByIds(@RequestBody List<Long> ids);
}
