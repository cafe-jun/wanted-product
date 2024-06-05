package com.example.cafejun.domain.product;


import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.repository.product.ProductEntity;
import com.example.cafejun.repository.user.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Product {
    private Long id;
    private String name;
    private Double price;
    private ProductStatus status;
    private Integer quantity;

    private String createdBy;

    private LocalDateTime createdDate;
    private String modifiedBy;

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .name(name)
                .price(price)
                .status(status)
                .quantity(quantity)
                .createdBy(createdBy)
                .modifiedBy(modifiedBy)
                .createdDate(createdDate)
                .build();
    }

    public ProductItems toProductItems() {
        return ProductItems.builder()
                .id(id)
                .name(name)
                .price(price)
                .status(status)
                .quantity(quantity)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .build();
    }
}
