package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Type;
import mk.finki.ukim.wp.lab.service.BalloonService;
import mk.finki.ukim.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/")
@Controller
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("listBalloons",this.balloonService.listAll());
        return "listBalloons";
    }

    @GetMapping("/search")
    public String searchBalloons(@RequestParam String text,Model model){
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
        return "listBalloons";
    }

    @GetMapping("/add-balloon")
    public String addBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }
    @GetMapping("/edit-form/{id}")
    public String editBalloonPage(@PathVariable Long id, Model model) {
        if(this.balloonService.findById(id).isPresent()){
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();

            model.addAttribute("manufacturers", manufacturers);

            model.addAttribute("balloon", balloon);
            return "add-balloon";
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
}
