package com.example.cafejun.repository.product;

import com.example.cafejun.domain.product.Product;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReader {

    private final ProductRepository productRepository;


    public List<Product> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }
    public Product findById(Long id, boolean isEmpty) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(isEmpty) {
            productEntity.orElseThrow(() -> new NotFoundException("제품 정보를 찾을 수 없습니다."));
        }
        log.info("userEntity : {}",productEntity.toString());
        return productEntity.get().toDomain();
    }
}
