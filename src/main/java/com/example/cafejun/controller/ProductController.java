package com.example.cafejun.controller;

import com.example.cafejun.config.util.SecurityUtil;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.dto.auth.request.SignInRequest;
import com.example.cafejun.dto.product.request.CreateProductDto;
import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.service.product.ProductService;
import io.jsonwebtoken.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "제품 API")
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
@RestController()
public class ProductController {

    private final ProductService productService;
    @GetMapping("")
    @Operation(summary = "제품 리스트 출력", description = "제품 전체 리스트 출력 APi")
    @ApiResponse(responseCode = "200", description = "제품 리스트 출력 성공")
    public ResponseEntity<List<ProductItems>> list() {
        List<ProductItems> productItems = productService.findAll();
        return ResponseEntity.ok(productItems);
    }

    @GetMapping(":id")
    @Operation(summary = "제품 리스트 상세 조회", description = "제품 리스트 상세 조회 API")
    @ApiResponse(responseCode = "200", description = "제품 리스트 상세 정보 출력 성공")
    public String detail() {
        return "logout";
    }

    @PostMapping("")
    @Operation(summary = "제품 등록", description = "제품 등록 API")
    @ApiResponse(responseCode = "200", description = "제품 생성 성공")
    public void create(@RequestBody @Valid CreateProductDto dto) {
        String username = SecurityUtil.getCurrentUsername().get();
        productService.register(dto,username);
        return;
    }

    @PutMapping(":id")
    @Operation(summary = "제품 정보 수정", description = "제품 정보 수정 API")
    @ApiResponse(responseCode = "200", description = "제품 정보 수정 성공")
    public String update() {
        return "logout";
    }
}
