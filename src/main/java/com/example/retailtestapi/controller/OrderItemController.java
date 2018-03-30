package com.example.retailtestapi.controller;

import com.example.retailtestapi.exception.ResourceNotFoundException;
import com.example.retailtestapi.model.Order;
import com.example.retailtestapi.model.OrderItem;
import com.example.retailtestapi.repository.OrderItemRepository;
import com.example.retailtestapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    // Get all order items
    @GetMapping("/orderItems")
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get order items by id
    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItem(@PathVariable(value = "id") Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item", "id", id));
    }

    // Get order items by orderId
    @GetMapping("/orderItems/forOrder/{id}")
    public Set<OrderItem> getOrderItemsForOrder(@PathVariable(value="id") Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return order.getOrderItems();
    }

    // Create a new order item
    @PostMapping("/orderItems")
    public OrderItem createOrderItem(@Valid @RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
