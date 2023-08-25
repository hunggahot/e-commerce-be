package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.CartItem;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.CartItemException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.response.ApiResponse;
import com.example.ecommercebe.service.cartItem.CartItemService;
import com.example.ecommercebe.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart_items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @PutMapping("/{cartItemId}")
    @Operation(description = "Update a cart item")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt
    ) throws CartItemException, UserException {
        // You might need to handle user authentication here.
        User user = userService.findUserProfileByJwt(jwt); // Get user based on JWT

        cartItem.setUserId(user.getId()); // Set the user's ID for the cart item
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    @Operation(description = "Remove a cart item by ID")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt) throws CartItemException, UserException {
        User user = userService.findUserProfileByJwt(jwt);

        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Cart item removed successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
