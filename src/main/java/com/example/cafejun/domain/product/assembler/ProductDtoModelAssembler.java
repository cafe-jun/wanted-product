package com.example.cafejun.domain.product.assembler;

import com.example.cafejun.controller.ProductController;
import com.example.cafejun.dto.product.response.ProductItems;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductDtoModelAssembler implements RepresentationModelAssembler<ProductItems, EntityModel<ProductItems>> {

    @Override
    public EntityModel<ProductItems> toModel(ProductItems productItems) {
        return EntityModel.of(productItems,
                linkTo(methodOn(ProductController.class).detail(productItems.id())).withSelfRel(),
                linkTo(methodOn(ProductController.class).list()).withRel("product-list")
        );
    }
}
