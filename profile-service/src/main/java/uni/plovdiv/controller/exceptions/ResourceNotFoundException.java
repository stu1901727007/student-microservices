package uni.plovdiv.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException
{
    public ResourceNotFoundException(HttpStatus httpStatus, String exception) {
        super(httpStatus, exception);
    }
}