package com.stewie.productservice.service;

import com.stewie.productservice.dto.ProductRequest;
import com.stewie.productservice.dto.ProductResponse;
import com.stewie.productservice.model.Product;
import com.stewie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Saving product " + product.getName());
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(this::convertToDto).toList();
    }

    private ProductResponse convertToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
