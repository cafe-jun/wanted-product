package com.example.cafejun.dto.product.response;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.product.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public record ProductItems(@Schema(description = "제품 ID", example = "1") Long id,
                           @Schema(description = "제품 이름", example = "아메리카노") String name,
                           @Schema(description = "제품 가격", example = "3000") Double price,
                           @Schema(description = "제품 상태") ProductStatus status,
                           @Schema(description = "제품 수량", example = "10") Integer quantity,
                           @Schema(description = "등록 계정", example = "admin") String createdBy,
                           @Schema(description = "등록일", example = "2024-01-01") LocalDateTime createdDate) {
    @Builder
    public ProductItems(Long id, String name, Double price, ProductStatus status, Integer quantity, String createdBy, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.createdDate = createdDate;
    }

    public static ProductItems from(Product product) {
        return ProductItems.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .quantity(product.getQuantity())
                .createdDate(product.getCreatedDate())
                .build();
    }
}
