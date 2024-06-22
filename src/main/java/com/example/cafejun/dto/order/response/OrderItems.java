package com.example.cafejun.dto.order.response;

import com.example.cafejun.domain.order.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {
        @Schema(description = "주문 ID", example = "1")
        private Long id;
        @Schema(description = "상품 ID", example = "1")
        private Long productId;

        @Schema(description = "주문 수량", example = "1")
        private Integer quantity;
        @Schema(description = "주문 가격", example = "3000")
        private Double price;
        @Schema(description = "주문 상태")
        private OrderStatus status;


}
