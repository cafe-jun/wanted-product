package com.example.cafejun.domain.order.assembler;

import com.example.cafejun.controller.OrderController;
import com.example.cafejun.controller.ProductController;
import com.example.cafejun.dto.order.response.OrderItems;
import com.example.cafejun.dto.product.response.ProductItems;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderDtoModelAssembler implements RepresentationModelAssembler<ProductItems, EntityModel<ProductItems>> {

    @Override
    public EntityModel<ProductItems> toModel(OrderItems orderItems) {
        return EntityModel.of(orderItems,
                linkTo(methodOn(OrderController.class).getOrderDetail(orderItems.).withSelfRel(),
                linkTo(methodOn(OrderController.class).getOrderList().withRel("product-list")
        );
    }
}
