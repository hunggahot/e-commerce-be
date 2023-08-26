package com.example.ecommercebe.service.orderItem;

import com.example.ecommercebe.entity.OrderItem;
import com.example.ecommercebe.repository.OrderItemRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemServiceImplementation implements OrderItemService{

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
