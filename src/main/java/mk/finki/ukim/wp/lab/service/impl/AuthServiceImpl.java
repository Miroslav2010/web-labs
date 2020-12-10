package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.models.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.models.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.lab.models.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.models.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryUserRepository;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password);
    }


    @Override
    public User register(String username, String password, String repeatPassword) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username)!=null)
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username,password);
        this.userRepository.deleteByUsername(username);
        return userRepository.save(user);
    }

}
