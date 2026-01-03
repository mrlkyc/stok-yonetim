package com.example.stok_yonetim.service;

import com.example.stok_yonetim.entity.Product;
import com.example.stok_yonetim.exception.InsufficientStockException;
import com.example.stok_yonetim.exception.ProductNotFoundException;
import com.example.stok_yonetim.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // ✅ POZİTİF SENARYO
    @Test
    void stokYeterliyseDusurulmeli() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setStock(10);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.decreaseStock(1L, 3);

        assertNotNull(result);
        assertEquals(7, result.getStock());
        verify(productRepository).save(product);
    }

    // ❌ YETERSİZ STOK
    @Test
    void stokYetersizseExceptionFirlatilmali() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setStock(2);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        assertThrows(
                InsufficientStockException.class,
                () -> productService.decreaseStock(1L, 5)
        );

        verify(productRepository, never()).save(any());
    }

    // ❌ ÜRÜN YOK
    @Test
    void urunYoksaExceptionFirlatilmali() {
        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.decreaseStock(99L, 1)
        );
    }

    // ❌ GEÇERSİZ MİKTAR
    @Test
    void miktarNegatifseExceptionFirlatilmali() {
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.decreaseStock(1L, -2)
        );
    }
}
