package com.Ecomerce.sale_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDto {

    @Schema(description = "Unique identifier of the product", example = "101")
    private Long productId;
    @Schema(description = "Quantity of the product in the cart", example = "2")
    private Integer quantity;
    @Schema(description = "Name of the product", example = "Wireless Mouse")
    private String name;
    @Schema(description = "Price of the product", example = "25.50")
    private Double price;
}
