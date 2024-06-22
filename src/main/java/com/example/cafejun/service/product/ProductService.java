package com.example.cafejun.service.product;


import com.example.cafejun.domain.order.Order;
import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.dto.product.request.CreateProductDto;
import com.example.cafejun.dto.product.request.UpdateProductDto;
import com.example.cafejun.dto.product.response.ProductItems;
import com.example.cafejun.repository.order.OrderReader;
import com.example.cafejun.repository.product.ProductAppendix;
import com.example.cafejun.repository.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductReader productReader;

    private final ProductAppendix productAppendix;
    private final OrderReader orderReader;

    public List<ProductItems> findAll() {
       List<Product> productList = productReader.findAll();
       return productList.stream().map(ProductItems::from).toList();
    }
    public ProductItems findById(Long id,String username){
        Product product =  productReader.findById(id,true);
        log.info("product -> {} ",product.toString());
        List<Order> order = orderReader.findByProductId(product.getId());
        return ProductItems.from(product);
    }
    public void register(CreateProductDto dto,String username) {
        Product product = dto.toProduct(username);
        product.validatePrice(dto.getPrice());
        product.validateQuantity(dto.getQuantity());
        productAppendix.saveProduct(dto.toProduct(username));
        log.info("register");
    }

    public void update(Long id, UpdateProductDto dto) {
        Product product = productReader.findById(id,true);
        product.updateProduct(dto);
        productAppendix.saveProduct(product);
        log.info("update -> {} ",product.toString());
    }

}

