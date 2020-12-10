package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.User;

public interface AuthService {
    User login(String username,String password);
    User register(String username,String password,String repeatPassword);
}
