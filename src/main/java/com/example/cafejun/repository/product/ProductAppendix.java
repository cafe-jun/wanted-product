package com.example.cafejun.repository.product;


import com.example.cafejun.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAppendix {
    private final ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product.toEntity());
    }

}
