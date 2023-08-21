package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
