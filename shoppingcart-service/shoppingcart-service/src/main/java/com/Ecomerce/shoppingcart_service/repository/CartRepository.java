package com.Ecomerce.shoppingcart_service.repository;

import com.Ecomerce.shoppingcart_service.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByUserId(Long userId);

    @EntityGraph(attributePaths = "items")
    Optional<Cart> findWithItemsById(Long id);


    Optional<Cart> findWithItemsByUserId(Long userId);

    class ProductApiFallback {
    }
}
