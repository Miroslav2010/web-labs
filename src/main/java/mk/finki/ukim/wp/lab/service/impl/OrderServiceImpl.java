package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.repository.BalloonRepository;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    BalloonRepository balloonRepository;

    public OrderServiceImpl(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public Order placeOrder(String balloonColor,String balloonSize, String clientName, String address) {
        return balloonRepository.placeOrder(clientName,address,balloonColor,balloonSize);
    }

    @Override
    public List<Order> getOrders(){
         return balloonRepository.getOrders();
    }
}