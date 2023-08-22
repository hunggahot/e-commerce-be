package com.example.ecommercebe.service.cartItem;

import com.example.ecommercebe.entity.Cart;
import com.example.ecommercebe.entity.CartItem;
import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.exception.CartItemException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.repository.CartItemRepository;
import com.example.ecommercebe.repository.CartRepository;
import com.example.ecommercebe.service.cart.CartService;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService{

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartService cartService) {
        return null;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        return null;
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return null;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        return null;
    }
}
