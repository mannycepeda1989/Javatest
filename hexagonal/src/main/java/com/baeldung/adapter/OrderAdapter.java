package com.baeldung.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.domain.Order;
import com.baeldung.port.OrderService;

@RestController
@RequestMapping("/order/")
public class OrderAdapter {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("create/{amount}")
    public void createOrder(@PathVariable Long amount) {
        orderService.createOrder(amount);
    }
    
    @GetMapping("view/{id}")
    public Order view(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    
}
