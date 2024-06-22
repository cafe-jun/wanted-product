package com.example.cafejun.repository.order;

import com.example.cafejun.domain.order.Order;
import com.example.cafejun.repository.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderReader {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream()
                .map(OrderEntity::toDomain)
                .collect(Collectors.toList());
    }

    public Order findById(Long id, boolean isEmpty) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if(isEmpty) {
            orderEntity.orElseThrow(() -> new NotFoundException("주문 정보를 찾을 수 없습니다."));
        }
        return orderEntity.get().toDomain();
    }
    public List<Order> findByProductId(Long productId) {
        List<OrderEntity> orderEntities = orderRepository.findByProductId(productId);
        return orderEntities.stream().map(OrderEntity::toDomain).collect(Collectors.toList());
    }
}
