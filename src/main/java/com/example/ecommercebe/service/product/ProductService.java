package com.example.ecommercebe.service.product;

import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest req);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product req) throws ProductException;

    Product findProductById(long id) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<Product> getAllProduct(String category, List<String> colors, List<String> size,
                                Integer minPrice, Integer maxPrice, Integer minDiscount,
                                String sort ,String stock, Integer pageNumber, Integer pageSize);

    List<Product> importProductData(MultipartFile file) throws ProductException;

    List<Product> findProductVariations(Long productId);

    Product findProductVariationById(Long productId, Long variationId) throws ProductException;

    Product createProductVariation(Long productId, Long variationId) throws ProductException;

    void deleteProductVariation(Long productId, Long variationId) throws ProductException;

    Product updateProductVariation(Long productId, long variationId, Product req) throws ProductException;
}
