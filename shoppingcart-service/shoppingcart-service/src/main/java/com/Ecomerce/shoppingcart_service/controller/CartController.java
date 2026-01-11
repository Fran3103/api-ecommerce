package com.Ecomerce.shoppingcart_service.controller;


import com.Ecomerce.shoppingcart_service.dto.AddItemRequest;
import com.Ecomerce.shoppingcart_service.dto.ApiError;
import com.Ecomerce.shoppingcart_service.dto.CartDto;
import com.Ecomerce.shoppingcart_service.entity.Cart;
import com.Ecomerce.shoppingcart_service.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name= "Cart Controller", description = "APIs for managing shopping carts")
@ApiResponses(
        {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ApiError.class))),
                @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
        }
)
@RequestMapping("/carts")
public class CartController {


    @Autowired
    public CartService cartService;

    @Operation(summary = "Get or create cart by user ID", description = "Retrieves the shopping cart for a user, or creates one if it doesn't exist")
    @GetMapping("/cart/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getorCreateCart(userId);
    }

    @Operation(summary = "Create cart for user", description = "Creates a new shopping cart for the specified user")
    @PostMapping("/createcart/{userid}")
    public Cart createCart(@PathVariable Long userid) {
        return cartService.getorCreateCart(userid);
    }

    @Operation(summary = "Add item to cart", description = "Adds a product to the user's shopping cart with the specified quantity")
    @PostMapping("/additem")
    public Cart addItem(@RequestBody AddItemRequest request) {

        return cartService.addItem(request.getUserId(), request.getProductId(), request.getQuantity());
    }

    @Operation(summary = "Get cart by ID", description = "Retrieves the shopping cart details by its ID")
    @GetMapping("cart/cart/{cartId}")
    public CartDto getCartById(@PathVariable Long cartId) {

        System.out.println("paso por aca" );
        return cartService.getCartDto(cartId);
    }
}
