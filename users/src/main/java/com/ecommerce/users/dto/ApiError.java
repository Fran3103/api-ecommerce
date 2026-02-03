package com.ecommerce.users.dto;

public record ApiError(
        String message,
        String path,
        int status,
        String timestamp
) {
}
