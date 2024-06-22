package com.example.cafejun.domain.order;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.repository.order.OrderEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Order {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;

    private OrderStatus status;


    public void purchaseConfirmed () {
        this.status = OrderStatus.PURCHASE_CONFIRMED;
    }

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .status(status)
                .build();
    }
}
