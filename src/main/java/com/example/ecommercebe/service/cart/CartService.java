package com.example.ecommercebe.service.cart;

import com.example.ecommercebe.entity.Cart;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
