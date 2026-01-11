package com.Ecomerce.sale_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CreateSaleRequest {

    @Schema(description = "Unique identifier of the shopping cart to create a sale from", example = "1")
    private Long cartId;
}
