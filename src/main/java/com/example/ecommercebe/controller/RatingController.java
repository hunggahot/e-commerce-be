package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.Rating;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.request.RatingRequest;
import com.example.ecommercebe.service.rating.RatingService;
import com.example.ecommercebe.service.user.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;


    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.createRating(req, user);

        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    private ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,
                                                           @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}
