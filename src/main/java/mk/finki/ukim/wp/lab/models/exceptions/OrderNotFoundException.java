package mk.finki.ukim.wp.lab.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }
}

