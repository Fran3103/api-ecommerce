package com.Ecomerce.sale_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SaleDto {

    @Schema(description = "Unique identifier of the sale", example = "1")
    private Long id;
    @Schema(description = "Date when the sale was made", example = "2024-06-15")
    private LocalDate date;
    @Schema(description = "Identifier of the associated shopping cart", example = "1")
    private Long cartId;
    @Schema(description = "Details of the shopping cart associated with the sale")
    private CartDto cart;
}
