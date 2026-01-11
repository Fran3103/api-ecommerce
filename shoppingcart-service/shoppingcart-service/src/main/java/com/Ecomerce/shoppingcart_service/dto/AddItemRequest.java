package com.Ecomerce.shoppingcart_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AddItemRequest {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long userId;
    @Schema(description = "Unique identifier of the product to add", example = "101")
    private Long productId;
    @Schema(description = "Quantity of the product to add", example = "2")
    private Integer quantity;
}
