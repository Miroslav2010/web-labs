package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.models.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.models.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.lab.models.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    String getLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword) {
        try{
            this.authService.register(username, password, repeatedPassword);
            return "redirect:/auth/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/auth/register?error=" + exception.getMessage();
        }
    }
}
