package com.example.ecommercebe.service.cartItem;

import com.example.ecommercebe.entity.Cart;
import com.example.ecommercebe.entity.CartItem;
import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.CartItemException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.repository.CartItemRepository;
import com.example.ecommercebe.repository.CartRepository;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem = findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new CartItemException("You can't remove another users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id: " + cartItemId);
    }
}
