package com.example.cafejun.domain.product;


import com.example.cafejun.dto.product.request.UpdateProductDto;
import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.repository.product.ProductEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Product {
    private Long id;
    private String name;
    private Double price;
    private ProductStatus status;
    private Integer quantity;

    private Long registerId;

    private LocalDateTime createdDate;

    private String modifiedBy;

    public void validateQuantity(Integer quantity) {
        if (quantity - this.quantity < 0) {
            throw new IllegalArgumentException("수량은 0 이상이어야 합니다.");
        }
    }

    public void validatePrice(Double price) {
        if (price - this.price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .name(name)
                .price(price)
                .status(status)
                .quantity(quantity)
                .registerId(registerId)
                .createdDate(createdDate)
                .build();
    }

    public void updateProduct(UpdateProductDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
    }

    public ProductItems toProductItems() {
        return ProductItems.builder()
                .id(id)
                .name(name)
                .price(price)
                .status(status)
                .quantity(quantity)
                .createdDate(createdDate)
                .build();
    }
}
