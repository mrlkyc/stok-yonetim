package com.example.stok_yonetim.service;

import com.example.stok_yonetim.dto.CreateProductRequest;
import com.example.stok_yonetim.entity.Product;
import com.example.stok_yonetim.exception.InsufficientStockException;
import com.example.stok_yonetim.exception.ProductNotFoundException;
import com.example.stok_yonetim.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        return productRepository.save(product);
    }
    public void createFromForm(String name, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        productRepository.save(product);
    }


    public Product decreaseStock(Long productId, int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Miktar pozitif olmalıdır");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Ürün bulunamadı: " + productId));

        if (product.getStock() < amount) {
            throw new InsufficientStockException("Yetersiz stok");
        }

        product.setStock(product.getStock() - amount);
        return productRepository.save(product);
    }
}
