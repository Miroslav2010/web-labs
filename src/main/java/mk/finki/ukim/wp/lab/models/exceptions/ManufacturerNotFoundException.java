package mk.finki.ukim.wp.lab.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException{

    public ManufacturerNotFoundException(Long id) {
        super(String.format("Manufacturer with id: %d was not found", id));
    }

}
