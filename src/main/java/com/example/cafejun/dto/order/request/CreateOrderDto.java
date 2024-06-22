package com.example.cafejun.dto.order.request;

import com.example.cafejun.domain.order.Order;
import com.example.cafejun.domain.order.OrderStatus;
import com.example.cafejun.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data

@Schema(description = "주문 생성 요청")
public class CreateOrderDto {

    @Schema(description = "상품 ID", example = "1")
    private Long productId;

    @Schema(description = "주문 수량", example = "1")
    private Integer quantity;


    @Builder
    public CreateOrderDto(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order toOrder(Product product) {
        return Order.builder()
                .quantity(quantity)
                .product(product)
                .price(product.getPrice()*quantity)
                .status(OrderStatus.COMPLETED)
                .build();
    }
}
