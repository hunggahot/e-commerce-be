package com.example.ecommercebe.service.orderItem;

import com.example.ecommercebe.entity.OrderItem;
import com.example.ecommercebe.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderItemServiceImplementation implements OrderItemService{

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
