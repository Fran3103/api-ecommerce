package com.Ecomerce.sale_service.dto;

public record ApiError(
        String message,
        String path,
        int status,
        String timestamp

) {}
