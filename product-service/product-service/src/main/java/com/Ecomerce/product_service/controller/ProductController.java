package com.Ecomerce.product_service.controller;

import com.Ecomerce.product_service.dtos.ApiError;
import com.Ecomerce.product_service.dtos.ProductDto;
import com.Ecomerce.product_service.entity.Product;
import com.Ecomerce.product_service.service.IProductService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Product Controller", description = "APIs for managing products")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema =  @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "Resource not found",content = @Content(schema =  @Schema(implementation = ApiError.class)))
})
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Value("${server.port}")
    private  int serverPort;

    @GetMapping("/all")
    public List<Product> getAllProducts() {

        System.out.println("Fetching a in port: " + serverPort);
        return productService.findAll();
    }

    @PostMapping("/ids")
    public List<Product> getProductsByIds(@RequestBody List<Long> ids) {
        System.out.println("Fetching a in port: " + serverPort);
        return productService.findByIds(ids);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        System.out.println("Fetching a in port: " + serverPort);
        return productService.findById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto product) {

         Product product1 = productService.findById(id);

            product1.setName(( product.getName() != null) ? product.getName() : product1.getName());
            product1.setPrice(product.getPrice() != null ? product.getPrice() : product1.getPrice());
            product1.setCode(product.getCode() != null ? product.getCode() : product1.getCode());
            product1.setBrand(product.getBrand() != null ? product.getBrand() : product1.getBrand());


        return productService.update(product1);

    }


    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }


    @GetMapping("/code/{code}")
    public Product getProductByCode(@PathVariable String code) {

      if (code == null) {
          new RuntimeException("Product not found");
        } return  productService.findByCode(code);
    }

}
