package com.pard.server.hw2nd.repository;

import com.pard.server.hw2nd.dto.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private static final Map<Integer, Product> productMap = new HashMap<>();
    private static Integer auto = 0;

    public Product save(Product product) {
        product.setId(++auto);
        productMap.put(product.getId(), product);
        return product;
    }

    public Product findById(Integer prodId) {
        return productMap.get(prodId);
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public void update(Integer prodId, Product updateProduct) {
        Product findProduct = findById(prodId);
        if (findProduct != null) {
            findProduct.setName(updateProduct.getName());
            findProduct.setPrice(updateProduct.getPrice());
            findProduct.setCount(updateProduct.getCount());
        }
    }

    public void delete(Integer prodId) {
        productMap.remove(prodId);
    }
}