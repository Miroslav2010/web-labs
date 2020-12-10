package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Order> listAllOrdersInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addOrderToShoppingCart(String username, Long productId);
}

