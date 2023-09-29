package com.pard.server.hw2nd.controller;


import com.pard.server.hw2nd.dto.Product;
import com.pard.server.hw2nd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class Controller {
    private final ProductRepository productRepo;

    @Autowired
    public Controller(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    @PostMapping("/add")
    public String prodAdd(@RequestBody Product product){
        productRepo.save(product);
        return "추가 완료";
    }

    @GetMapping("/findAll")
    public List<Product> prodFind(){
        return productRepo.findAll();
    }

    @GetMapping("/findOne/{ProdId}")
    public Product prodFindOne(@PathVariable Integer ProdId){
        Product product = productRepo.findById(ProdId);
        return product;
    }

    @PatchMapping("/update/{ProdId}")
    public String prodUpdate(@PathVariable Integer ProdId, @RequestBody Product product){
        productRepo.update(ProdId,product);
        return "교체 완료";
    }

    @DeleteMapping("/delete/{ProdId}")
    public String prodDelete(@PathVariable Integer ProdId){
        productRepo.delete(ProdId);
        return "삭제 완료";
    }

}