package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository {


    public Order placeOrder(User user, String balloonColor, String balloonSize){
        Order order = new Order(balloonColor,balloonSize,user);
        order.setOrderId((long)DataHolder.orderList.size());
        DataHolder.orderList.add(order);
        return order;
    }
    public List<Order> getOrders(){
        return DataHolder.orderList;
    }

    public Optional<Order> findById(Long id){
        return DataHolder.orderList.stream().filter(i -> i.getOrderId().equals(id)).findFirst();
    }
}
