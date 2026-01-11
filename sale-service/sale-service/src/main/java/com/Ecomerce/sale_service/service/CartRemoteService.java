package com.Ecomerce.sale_service.service;

import com.Ecomerce.sale_service.dto.CartDto;
import com.Ecomerce.sale_service.repository.CartApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartRemoteService {

    @Autowired
    private CartApi cartApi;


    @CircuitBreaker(name = "cart-service", fallbackMethod = "fetchCartFallback")
    @Retry(name = "cart-service")
    public CartDto fetchCartById(Long cartId){
        return cartApi.getCartById(cartId);
    }

    private CartDto fetchCartFallback(Long cartId, Throwable throwable) {

        CartDto dto = new CartDto();
        dto.setId(cartId);
        dto.setTotalPrice(0.0);
        dto.setItems(List.of());
        System.out.println("FALLBACK: shoppingcart-service no disponible, devolviendo carrito vacío para cartId=" + cartId);

        return dto;
    }
}
