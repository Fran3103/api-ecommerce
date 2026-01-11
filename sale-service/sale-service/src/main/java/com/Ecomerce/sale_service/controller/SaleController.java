package com.Ecomerce.sale_service.controller;

import com.Ecomerce.sale_service.dto.ApiError;
import com.Ecomerce.sale_service.dto.CreateSaleRequest;
import com.Ecomerce.sale_service.dto.SaleDto;
import com.Ecomerce.sale_service.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sale Controller", description = "APIs for managing sales")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "Resource not found",
                content = @Content(schema = @Schema(implementation = ApiError.class)))
})
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Operation(summary = "Create a new sale from a shopping cart" , description = "Creates a new sale based on the provided shopping cart ID")
    @PostMapping
    public SaleDto createSale(@RequestBody CreateSaleRequest request) {
        return saleService.createSale(request.getCartId());
    }

    @Operation(summary = "Get sale by ID" , description = "Retrieves the details of a sale by its ID")
    @GetMapping("/{saleId}")
    public SaleDto getSaleById(@PathVariable Long saleId) {
        return saleService.getSaleById(saleId);
    }

}
