package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.models.*;
import mk.finki.ukim.wp.lab.service.BalloonService;
import mk.finki.ukim.wp.lab.service.ManufacturerService;
import mk.finki.ukim.wp.lab.service.OrderService;
import mk.finki.ukim.wp.lab.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/")
@Controller
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request){
        request.getSession().setAttribute("chosenColor","");
        request.getSession().setAttribute("chosenSize","");
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("listBalloons",this.balloonService.listAll());
        model.addAttribute("bodyContent","listBalloons");
        return "master-page";
    }

    @GetMapping("/search")
    public String searchBalloons(@RequestParam String text,Model model,HttpServletRequest request){
        request.getSession().setAttribute("chosenColor","");
        request.getSession().setAttribute("chosenSize","");
        if(text!= null && !text.isEmpty()){
            try{
                if(text.length()==1){
                    model.addAttribute("listBalloons", this.balloonService.searchByType(text));
                }else {
                    model.addAttribute("listBalloons", this.balloonService.searchByNameOrDescription(text));
                }
            }
            catch(Exception e){
                String message = e.getMessage();
            }

        }
        else
        {
            model.addAttribute("listBalloons", this.balloonService.listAll());
        }
        model.addAttribute("bodyContent","listBalloons");
        return "master-page";
    }

    @GetMapping("/add-balloon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent","add-balloon");
        return "master-page";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBalloonPage(@PathVariable Long id, Model model) {
        if(this.balloonService.findById(id).isPresent()){
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();

            model.addAttribute("manufacturers", manufacturers);

            model.addAttribute("balloon", balloon);
            model.addAttribute("bodyContent","add-balloon");
            return "master-page";
        }
        return "redirect:/?error=BalloonNotFound";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam Long id, @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer,
                              @RequestParam String type){
        if(id == 0){
            Type tip = Type.valueOf(type);
            this.balloonService.add(name, description, manufacturer,tip);
        }
        else{
            Type tip = Type.valueOf(type);
            this.balloonService.edit(id, name, description, manufacturer,tip);
        }
        return "redirect:/";
    }
    @PostMapping("/redirectToSize/{name}")
    public void redirectToSize(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().setAttribute("chosenColor",name);
        response.sendRedirect("/selectBalloon");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/selectBalloon")
    public String getSelectBalloonSize(Model model){
        model.addAttribute("bodyContent","selectBalloonSize");
        return "master-page";
    }

    @PostMapping("/selectBalloon")
    public void postSelectBalloon(@RequestParam String size, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("chosenSize",size);
        response.sendRedirect("/usersOrder/confirm");
    }

    @GetMapping("/advancedSearch")
    public String advanceSearchBalloons(@RequestParam String text,Model model,HttpServletRequest request){
        request.getSession().setAttribute("chosenColor","");
        request.getSession().setAttribute("chosenSize","");
        if(text!= null && !text.isEmpty()){
            try{
                List<Balloon> fromType = new ArrayList<>();
                if(text.length()==1)
                    fromType = this.balloonService.searchByType(text);
                List<Balloon> fromDescriptionName = this.balloonService.findSthLikeDescOrName(text);
                //List<Balloon> fromName = this.balloonService.findSthLikeName(text);
                List<Balloon> result = new ArrayList<>(fromType);
                result.addAll(fromDescriptionName);
                //result.addAll(fromName);
                result.stream().distinct().collect(Collectors.toList());
                model.addAttribute("listBalloons", result);
            }
            catch(Exception e){
                String message = e.getMessage();
            }

        }
        else
        {
            model.addAttribute("listBalloons", this.balloonService.listAll());
        }
        model.addAttribute("bodyContent","listBalloons");
        return "master-page";
    }
}
