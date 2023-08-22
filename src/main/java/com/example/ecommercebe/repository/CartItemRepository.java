package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
