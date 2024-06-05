package com.example.cafejun.service.product;


import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.dto.product.request.CreateProductDto;
import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.repository.product.ProductAppendix;
import com.example.cafejun.repository.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductReader productReader;

    private final ProductAppendix productAppendix;


    public List<ProductItems> findAll() {
       List<Product> productList = productReader.findAll();
       return productList.stream().map(ProductItems::from).toList();
    }
    public ResponseEntity<Product> findById(Long id) {
        Product product =  productReader.findById(id,true);
        log.info("product : {}",product.toString());
        return ResponseEntity.ok(product);
    }
    public void register(CreateProductDto dto,String username) {

        productAppendix.saveProduct(dto.toProduct(username));
        log.info("register");
    }
}

