package com.example.stok_yonetim.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    protected Stock() {}

    public Stock(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // getters
    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Integer getQuantity() { return quantity; }

    // business methods
    public void increase(int amount) {
        this.quantity += amount;
    }

    public void decrease(int amount) {
        if (this.quantity < amount) {
            throw new IllegalStateException("Yetersiz stok");
        }
        this.quantity -= amount;
    }
}

