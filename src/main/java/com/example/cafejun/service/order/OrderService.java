package com.example.cafejun.service.order;


import com.example.cafejun.domain.order.Order;
import com.example.cafejun.domain.product.Product;
import com.example.cafejun.dto.order.request.CreateOrderDto;
import com.example.cafejun.repository.order.OrderAppendix;
import com.example.cafejun.repository.order.OrderReader;
import com.example.cafejun.repository.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderReader orderReader;

    private final ProductReader productReader;

    private final OrderAppendix orderAppendix;

    public void create(CreateOrderDto dto) {
        log.info("create order : {}", dto.toString());
        Product product = productReader.findById(dto.getProductId(), true);
        product.validateQuantity(dto.getQuantity());
        Order order = dto.toOrder(product);
        orderAppendix.saveProduct(order);
    }

    public void cancel(Long id) {
        log.info("cancel order : {}", id);
        Order order = orderReader.findById(id, true);
        order.cancel();
        orderAppendix.saveProduct(order);
    }

}
