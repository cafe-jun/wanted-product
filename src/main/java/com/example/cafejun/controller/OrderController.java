package com.example.cafejun.controller;

import com.example.cafejun.service.order.OrderService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order", description = "주문 API")
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
@RestController()
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    @Schema(description = "주문 생성", example = "1", required = true)
    public void createOrder() {
        log.debug("create order");
    }
    @PutMapping("/cancel")
    @Schema(description = "주문 취소", example = "1", required = true)
    public void cancelOrder() {
        log.debug("cancel order");
    }

    @GetMapping("/{id}")
    @Schema(description = "주문 상세 조회", example = "1", required = true)
    public void getOrderDetail() {
        log.debug("get order detail");
    }

    @GetMapping("")
    @Schema(description = "주문 리스트 조회", example = "1", required = true)
    public void getOrderList() {
        log.debug("get order list");
    }

    @Schema(description = "주문 수정", example = "1", required = true)
    public void updateOrder() {
        log.debug("update order");
    }

    @Schema(description = "주문 상태 변경", example = "1", required = true)
    public void changeOrderStatus() {
        log.debug("change order status");
    }
}
