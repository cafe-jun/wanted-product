package com.example.cafejun.controller;

import com.example.cafejun.common.annotation.UserSession;
import com.example.cafejun.common.api.Api;
import com.example.cafejun.config.util.SecurityUtil;
import com.example.cafejun.domain.product.assembler.ProductDtoModelAssembler;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.dto.product.request.CreateProductDto;
import com.example.cafejun.dto.product.request.UpdateProductDto;
import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Product", description = "제품 API")
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
@RestController()
public class ProductController {

    private final ProductService productService;
    private final ProductDtoModelAssembler modelAssembler;
    @GetMapping("")
    @Operation(summary = "제품 리스트 출력", description = "제품 전체 리스트 출력 APi")
    @ApiResponse(responseCode = "200", description = "제품 리스트 출력 성공")
    public Api<List<EntityModel<ProductItems>>> list() {
        List<EntityModel<ProductItems>> productItems = productService.findAll().stream()
                .map(productItem -> EntityModel.of(productItem,
                        linkTo(methodOn(ProductController.class).detail(productItem.id())).withSelfRel(),
                        linkTo(methodOn(ProductController.class).list()).withRel("product-list")
                )).collect(Collectors.toList());
        return Api.OK(productItems);
    }

    @GetMapping("{id}")
    @Operation(summary = "제품 리스트 상세 조회", description = "제품 리스트 상세 조회 API")
    @ApiResponse(responseCode = "200", description = "제품 리스트 상세 정보 출력 성공")
    public Api<EntityModel<ProductItems>> detail(
          @PathVariable("id")
          @Schema(description = "제품 ID", example = "1", required = true)
          Long id,
          @UserSession User user
    ) {
        log.debug("id: {}", id);
        ProductItems productItems = productService.findById();
        EntityModel<ProductItems> model = modelAssembler.toModel(productItems);
        return Api.OK(model);
    }

    @PostMapping("")
    @Operation(summary = "제품 등록", description = "제품 등록 API")
    @ApiResponse(responseCode = "200", description = "제품 생성 성공")
    public void create(@RequestBody @Valid CreateProductDto dto) {
        String username = SecurityUtil.getCurrentUsername().get();
        productService.register(dto,username);
        return;
    }

    @PutMapping("{id}")
    @Operation(summary = "제품 정보 수정", description = "제품 정보 수정 API")
    @ApiResponse(responseCode = "200", description = "제품 정보 수정 성공")
    public void update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateProductDto dto
    ) {
        productService.update(id,dto);
        return;
    }
}
