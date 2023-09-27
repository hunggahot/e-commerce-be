package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.Review;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.request.ReviewRequest;
import com.example.ecommercebe.service.review.ReviewService;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    private final UserService userService;


    @PostMapping("/product/{productId}/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @PathVariable Long productId,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        // Now you have access to the productId from the path variable
        // You can use it to associate the review with the product
        Review review = reviewService.createReview(req, user, productId);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId) throws UserException, ProductException {

        List<Review> reviews = reviewService.getAllReview(productId);

        return new ResponseEntity<>(reviews, HttpStatus.CREATED);
    }
}
