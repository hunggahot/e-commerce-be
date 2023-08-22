package com.example.ecommercebe.service.order;

import com.example.ecommercebe.entity.Address;
import com.example.ecommercebe.entity.Order;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.OrderException;
import com.example.ecommercebe.repository.CartRepository;
import com.example.ecommercebe.service.cart.CartService;
import com.example.ecommercebe.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService{

    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order comfirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
