package com.Ecomerce.sale_service.repository;

import com.Ecomerce.sale_service.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="shoppingcart-service")
public interface CartApi {

    @GetMapping("/carts/cart/cart/{cartId}")
    CartDto getCartById(@PathVariable Long cartId);
}
