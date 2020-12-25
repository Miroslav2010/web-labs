package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(String balloonColor,String balloonSize, String username) {
        User user = userRepository.findByUsername(username);
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