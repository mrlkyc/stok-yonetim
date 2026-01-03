package com.example.stok_yonetim.controller;

import com.example.stok_yonetim.entity.Product;
import com.example.stok_yonetim.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductPageController {

    private final ProductRepository productRepository;

    public ProductPageController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam String name,
                             @RequestParam int stock) {

        Product product = new Product();
        product.setName(name);
        product.setStock(stock);

        productRepository.save(product);

        return "redirect:/";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
