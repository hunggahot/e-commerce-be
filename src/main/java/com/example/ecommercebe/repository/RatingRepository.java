package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.product.is=:productId")
    List<Rating> getAllProductsRating(@Param("productId") Long productId);
}
