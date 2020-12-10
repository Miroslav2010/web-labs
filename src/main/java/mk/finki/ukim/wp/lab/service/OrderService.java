package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.User;

import java.util.List;
import java.util.Optional;

public interface OrderService{
    Order placeOrder(String balloonColor, String ballonSize, User user);
    List<Order> getOrders();
    Optional<Order> findById(Long id);
}