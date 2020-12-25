package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.models.ShoppingCart;
import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String user = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user);
        model.addAttribute("orders", this.shoppingCartService.listAllOrdersInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent","shopping-cart");
        return "master-page";
    }

    @PostMapping("/add-order/{id}")
    public String addOrderToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try{
            User user1 = (User) authentication.getPrincipal();
            String user = req.getRemoteUser();
            ShoppingCart shoppingCart = this.shoppingCartService.addOrderToShoppingCart(user1.getUsername(), id);
            return "redirect:/shopping-cart";
        }catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}

