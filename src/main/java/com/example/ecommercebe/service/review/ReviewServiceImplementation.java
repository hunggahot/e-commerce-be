package com.example.ecommercebe.service.review;

import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.entity.Review;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.repository.ProductRepository;
import com.example.ecommercebe.repository.ReviewRepository;
import com.example.ecommercebe.request.ReviewRequest;
import com.example.ecommercebe.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService{

    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest req, User user, Long productId) throws ProductException {

        Product product = productService.findProductById(productId);

        if (product == null) {
            // Handle the case where the product with the specified productId is not found.
            throw new ProductException("Product not found for productId: " + productId);
        }

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getALlProductsReview(productId);
    }
}
