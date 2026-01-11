package com.Ecomerce.shoppingcart_service.dto;

public record ApiError(
        String message,
        String path,
        int status,
        String timestamp
) {
}
