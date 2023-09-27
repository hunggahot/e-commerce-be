package com.example.ecommercebe.service.review;

import com.example.ecommercebe.entity.Review;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewRequest req, User user, Long productId) throws ProductException;
    List<Review> getAllReview(Long productId);
}
