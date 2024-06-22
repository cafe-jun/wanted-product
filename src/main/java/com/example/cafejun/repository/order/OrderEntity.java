package com.example.cafejun.repository.order;
import com.example.cafejun.domain.order.Order;
import com.example.cafejun.domain.order.OrderStatus;
import com.example.cafejun.repository.AuditField;
import com.example.cafejun.repository.product.ProductEntity;
import com.example.cafejun.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
public class OrderEntity extends AuditField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Builder
    public OrderEntity(Long productId,Integer quantity, Double price, OrderStatus status) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Order toDomain() {
        return Order.builder()
                .id(id)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .status(status)
                .build();
    }

}