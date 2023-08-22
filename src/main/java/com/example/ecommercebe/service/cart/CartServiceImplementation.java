package com.example.ecommercebe.service.cart;

import com.example.ecommercebe.entity.Cart;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.repository.CartRepository;
import com.example.ecommercebe.request.AddItemRequest;
import com.example.ecommercebe.service.cartItem.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService{

    private CartRepository cartRepository;
    private CartItemService cartItemService;

    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
