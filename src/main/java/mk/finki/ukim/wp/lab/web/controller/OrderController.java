package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.models.Order;
import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.service.OrderService;
import mk.finki.ukim.wp.lab.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usersOrder")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getOrderPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Order> orders = this.orderService.getOrders();
        model.addAttribute("orders", orders);
        return "usersOrder";
    }

    @GetMapping("/confirm")
    public String getConfirmPage(HttpServletRequest request,Model model){
        model.addAttribute("ipaddress",request.getRemoteAddr());
        model.addAttribute("clientAgent",request.getHeader("User-Agent"));
        model.addAttribute("bodyContent","confirmationInfo");
        return "master-page";
    }

    @PostMapping("/confirm")
    public void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getRemoteUser();
        Order order = orderService.placeOrder((String)request.getSession().getAttribute("chosenColor"),
                (String)request.getSession().getAttribute("chosenSize"),user);
        shoppingCartService.addOrderToShoppingCart(user, order.getOrderId());
        response.sendRedirect("/");
    }
}
