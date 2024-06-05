package com.example.cafejun.dto.product.request;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.product.ProductStatus;
import com.example.cafejun.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "상품 생성 DTO", title = "상품 생성 DTO")
public class CreateProductDto {


    @Schema(description = "상품명", example = "아메리카노")
    private String name;

    @Schema(description = "가격", example = "3000")
    private Double price;

    @Schema(description = "수량", example = "10")
    private Integer quantity;


    public Product toProduct(String username) {
        return Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .status(ProductStatus.ON_SALE)
                .createdBy(username)
                .modifiedBy(username)
                .build();
    }
}
