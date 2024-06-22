package com.example.cafejun.dto.product.request;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.product.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "상품 수정 DTO", title = "상품 수정 DTO")
public class UpdateProductDto {


    @Schema(description = "상품명", example = "아메리카노")
    private String name;

    @Schema(description = "가격", example = "3000")
    private Double price;

    @Schema(description = "수량", example = "10")
    private Integer quantity;



}
