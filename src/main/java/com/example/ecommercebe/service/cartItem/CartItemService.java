package com.example.ecommercebe.service.cartItem;

import com.example.ecommercebe.entity.Cart;
import com.example.ecommercebe.entity.CartItem;
import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.exception.CartItemException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.service.cart.CartService;

public interface CartItemService {

    CartItem createCartItem(CartService cartService);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById (Long cartItemId) throws CartItemException;
}
