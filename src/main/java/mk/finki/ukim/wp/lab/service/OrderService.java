package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.Order;

import java.util.List;

public interface OrderService{
    Order placeOrder(String balloonColor, String ballonSize, String clientName, String address);
    List<Order> getOrders();
}