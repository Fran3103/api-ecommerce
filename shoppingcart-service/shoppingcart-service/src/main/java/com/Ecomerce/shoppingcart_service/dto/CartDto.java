package com.Ecomerce.shoppingcart_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @Schema(description = "Unique identifier of the cart", example = "1")
    private Long id;

    @Schema(description = "List of items in the cart" , example = "[{item1}, {item2}]")
   private List<CartItemDto> items;

    @Schema(description = "Total price of all items in the cart", example = "150.75")
   private Double totalPrice;


}
