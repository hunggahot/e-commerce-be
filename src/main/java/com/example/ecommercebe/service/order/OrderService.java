package com.example.ecommercebe.service.order;

import com.example.ecommercebe.entity.Address;
import com.example.ecommercebe.entity.Order;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.OrderException;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, Address shippingAddress);

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> userOrderHistory(Long userId);

    Order placedOrder(Long orderId) throws OrderException;

    Order comfirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canceledOrder(Long orderId) throws OrderException;

    List<Order> getAllOrders();

    void deleteOrder(Long orderId) throws OrderException;
}
