package com.Ecomerce.shoppingcart_service.service;

import com.Ecomerce.shoppingcart_service.dto.CartDto;
import com.Ecomerce.shoppingcart_service.dto.CartItemDto;
import com.Ecomerce.shoppingcart_service.dto.ProductDto;
import com.Ecomerce.shoppingcart_service.entity.Cart;
import com.Ecomerce.shoppingcart_service.entity.CartItem;
import com.Ecomerce.shoppingcart_service.repository.CartItemRepository;
import com.Ecomerce.shoppingcart_service.repository.CartRepository;
import com.Ecomerce.shoppingcart_service.repository.IProductApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository itemRepo;

    @Autowired
    private IProductApi productApi;


    @Override
    public Cart getorCreateCart(Long userId) {

        // Try to find an existing cart for the user
        // If not found, create a new cart
        // Return the cart

        return cartRepo.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepo.save(newCart);
        });
    }

    @Override
    public Cart addItem(Long userId, Long productId, Integer quantity) {

        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Cart cart = getorCreateCart(userId);
        // Check if the item already exists in the cart
        // If it exists, update the quantity
        // If not, create a new cart item



        CartItem item = itemRepo.findByCartIdAndProductId(cart.getId(), productId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProductId(productId);
                    newItem.setQuantity(0);
                    return newItem;
                });
        item.setQuantity(item.getQuantity() + quantity);
        itemRepo.save(item);
        // Return the updated cart with items
        return cartRepo.findWithItemsById(cart.getId()).orElse(cart);
    }

    @Override
    public Cart updateItemQuantity(Long userId, Long productId, Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        // Get or create cart
        Cart cart = getorCreateCart(userId);


        // Find the item in the cart
        // If not found, throw an exception
        CartItem item = itemRepo.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

        item.setQuantity(quantity);
        itemRepo.save(item);

        // Return the updated cart with items
        return cartRepo.findWithItemsById(cart.getId()).orElse(cart);
    }

    @Override
    public void removeItem(Long userId, Long productId) {

        Cart cart = getorCreateCart(userId);
        // Find the item in the cart
        // If not found, throw an exception
        CartItem item = itemRepo.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

        itemRepo.delete(item);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getorCreateCart(userId);
        itemRepo.deleteByCartId(cart.getId());
    }

    @Override
    public CartDto getCartDto(Long userId) {

        // Retrieve the cart for the user
        Cart cart = cartRepo.findWithItemsByUserId(userId).orElseThrow(()-> new RuntimeException("Cart not found"));

        // For each item in the cart, retrieve product details from Product Service
        List<Long> ids  = cart.getItems().stream().map(CartItem::getProductId).toList();

        // Call Product Service to get product details
        List<ProductDto> producList = fetchProductsByIds(ids);

        // Map products by ID for easy lookup
        // Create a map of productId to ProductDto
        Map<Long, ProductDto> productsById = producList.stream()
                .collect(Collectors.toMap(ProductDto::getId, p -> p));


        // Map CartItems and Product details to CartItemDto
        List<CartItemDto> itemDto = cart.getItems().stream().map(ci -> {
            ProductDto p = productsById.get(ci.getProductId());
            CartItemDto dto = new CartItemDto();
            dto.setProductId(ci.getProductId());
            dto.setQuantity(ci.getQuantity());


            dto.setName(p != null ? p.getName() : "Unknown Product");
            dto.setPrice(p != null && p.getPrice() != null ? p.getPrice() : 0.0);

            return dto;
        }).toList();

        double total = itemDto.stream()
                .filter(i -> i.getPrice() != null)
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        // Construct CartDto
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setItems(itemDto);
        cartDto.setTotalPrice( total);

        return cartDto;

    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fetchProductsFallback")
    @Retry(name = "productService")
    public List<ProductDto> fetchProductsByIds(List<Long> ids) {
        return productApi.getProductsByIds(ids);
    }


    private List<ProductDto> fetchProductsFallback(List<Long> ids, Throwable ex) {
        return List.of(); // fallback: lista vacía (tu lógica arriba marca “no disponible”)
    }
}
