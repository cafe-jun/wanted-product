package com.example.cafejun.repository.order;


import com.example.cafejun.domain.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderAppendix {
    private final OrderRepository orderRepository;
    public void saveProduct(Order order) {
        orderRepository.save(order.toEntity());
    }
}
