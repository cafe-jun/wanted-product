package com.example.cafejun.repository.product;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.product.ProductStatus;
import com.example.cafejun.repository.AuditField;
import com.example.cafejun.repository.order.OrderEntity;
import com.example.cafejun.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
@Table(indexes = {
        @Index(columnList = "name")},
        name = "product")
@Entity
public class ProductEntity extends AuditField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    private Integer quantity;

    @Builder
    // 생성자
    public ProductEntity(String name, Double price, ProductStatus status, Integer quantity, String createdBy, String modifiedBy, LocalDateTime createdDate) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;

    }


    public Product toDomain() {
        return Product.builder()
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
