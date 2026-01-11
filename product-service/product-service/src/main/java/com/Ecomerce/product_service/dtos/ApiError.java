package com.Ecomerce.product_service.dtos;

public record ApiError(
        String message,
        String path,
        int status,
        String timestamp
) {
}
