package com.Ecomerce.sale_service.service;

import com.Ecomerce.sale_service.dto.SaleDto;

public interface ISaleService {

    SaleDto createSale(Long cartId);

    SaleDto getSaleById(Long saleId);
}
