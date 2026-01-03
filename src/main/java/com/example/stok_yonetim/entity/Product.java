package com.example.stok_yonetim.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stock;   // 👈 ÖNEMLİ

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {     // 👈 ÖNEMLİ
        return stock;
    }

    public void setStock(int stock) {   // 👈 ÖNEMLİ
        this.stock = stock;
    }
}
