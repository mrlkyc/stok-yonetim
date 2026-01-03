package com.example.stok_yonetim.repository;

import com.example.stok_yonetim.entity.Product;   // 👈 EKSİK OLAN SATIR
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

