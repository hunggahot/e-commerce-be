package com.example.ecommercebe.service.rating;

import com.example.ecommercebe.entity.Rating;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.RatingRequest;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingRequest req, User user) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
