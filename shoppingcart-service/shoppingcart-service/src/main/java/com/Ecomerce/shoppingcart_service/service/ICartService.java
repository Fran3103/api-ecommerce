package com.Ecomerce.shoppingcart_service.service;

import com.Ecomerce.shoppingcart_service.dto.CartDto;
import com.Ecomerce.shoppingcart_service.entity.Cart;

public interface ICartService {

    Cart getorCreateCart(Long userId);

    Cart addItem (Long userId, Long productId, Integer quantity);

    Cart updateItemQuantity(Long userId, Long productId, Integer quantity);

    void removeItem (Long userId, Long productId);

    void clearCart (Long userId);

    CartDto getCartDto(Long userId);
}
