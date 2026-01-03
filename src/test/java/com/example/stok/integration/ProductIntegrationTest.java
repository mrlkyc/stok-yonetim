package com.example.stok.integration;

import com.example.stok_yonetim.StokYonetimApplication;
import com.example.stok_yonetim.entity.Product;
import com.example.stok_yonetim.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StokYonetimApplication.class)
@ActiveProfiles("test")
class ProductIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void urunKaydedilmeli() {
        Product product = new Product();
        product.setName("Integration Test Ürünü");
        product.setStock(50);

        Product kaydedilen = productRepository.save(product);

        assertThat(kaydedilen.getId()).isNotNull();
        assertThat(kaydedilen.getStock()).isEqualTo(50);
    }
}
