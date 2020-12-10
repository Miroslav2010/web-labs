package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryOrderRepository;
import mk.finki.ukim.wp.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor,String balloonSize, User user) {
        return orderRepository.save(new Order(balloonColor,balloonSize,user));
    }

    @Override
    public List<Order> getOrders(){
         return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }
}