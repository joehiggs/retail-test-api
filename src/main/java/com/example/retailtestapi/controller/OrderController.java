package com.example.retailtestapi.controller;

import com.example.retailtestapi.exception.ResourceNotFoundException;
import com.example.retailtestapi.model.Order;
import com.example.retailtestapi.model.OrderItem;
import com.example.retailtestapi.payload.OrderItemRequest;
import com.example.retailtestapi.payload.OrderRequest;
import com.example.retailtestapi.repository.OrderItemRepository;
import com.example.retailtestapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200"})
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    // Get all orders
    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create new order
    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getCustomerName(), orderRequest.getAddress());
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            OrderItem item = new OrderItem(itemRequest.getItemName(), itemRequest.getQuantity());
            item.setOrder(order);
            order.getOrderItems().add(item);
        }
        return orderRepository.save(order);
    }

    // Get a single order
    @RequestMapping("/orders/{id}")
    public Order getOrder(@PathVariable(value="id") Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
    }


}
