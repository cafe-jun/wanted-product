package com.example.cafejun.domain.order;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.repository.order.OrderEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;

    private OrderStatus status;

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .product_id(productId)
                .quantity(quantity)
                .status(status)
                .build();
    }
}
