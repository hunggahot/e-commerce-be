package com.example.ecommercebe.service.product;

import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    @Override
    public Product createProduct(CreateProductRequest req) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        return null;
    }

    @Override
    public Product findProductById(long id) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, String stock, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
