package com.Ecomerce.sale_service.service;

import com.Ecomerce.sale_service.dto.CartDto;
import com.Ecomerce.sale_service.dto.SaleDto;
import com.Ecomerce.sale_service.entity.Sale;
import com.Ecomerce.sale_service.repository.CartApi;
import com.Ecomerce.sale_service.repository.SaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService{


    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private CartRemoteService cartService;

    @Override
    public SaleDto createSale(Long cartId) {

        CartDto cart = cartService.fetchCartById(cartId);

        Sale sale = new Sale();

        sale.setCartId(cartId);
        sale.setDate(LocalDate.now());

        Sale saved = saleRepo.save(sale);

        return new SaleDto(saved.getId(), saved.getDate(), saved.getCartId(), cart);

    }

    @Override
    public SaleDto getSaleById(Long saleId) {

        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(()-> new RuntimeException("Sale not found with id: " + saleId));

        CartDto cart = cartService.fetchCartById(sale.getCartId());

        return new SaleDto(sale.getId(), sale.getDate(), sale.getCartId(), cart);
    }



}
