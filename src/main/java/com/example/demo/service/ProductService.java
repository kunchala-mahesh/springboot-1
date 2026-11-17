package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final Map<Long, Product> productStorage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductService() {
        // Sample seed data
        saveSeed(new Product(null, "Wireless Mouse", "Ergonomic 2.4GHz wireless optical mouse", 29.99, 50));
        saveSeed(new Product(null, "Mechanical Keyboard", "RGB mechanical gaming keyboard with blue switches", 79.99, 30));
        saveSeed(new Product(null, "USB-C Hub", "7-in-1 multi-port adapter with 4K HDMI and 100W PD", 45.50, 80));
    }

    private void saveSeed(Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        productStorage.put(id, product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productStorage.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    public Product createProduct(Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        productStorage.put(id, product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        if (!productStorage.containsKey(id)) {
            return Optional.empty();
        }
        updatedProduct.setId(id);
        productStorage.put(id, updatedProduct);
        return Optional.of(updatedProduct);
    }

    public boolean deleteProduct(Long id) {
        return productStorage.remove(id) != null;
    }
}
