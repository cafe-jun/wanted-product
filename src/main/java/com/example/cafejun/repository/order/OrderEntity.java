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

    @Column(nullable = false)
    private Long product_id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Builder
    public OrderEntity(Long product_id,Integer quantity, Double price, OrderStatus status) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Order toDomain() {
        return Order.builder()
                .id(id)
//                .product(product.toDomain())
                .quantity(quantity)
                .price(price)
                .status(status)
                .build();
    }

}