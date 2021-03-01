package uni.plovdiv.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException
{
    public ValidationException(HttpStatus httpStatus, String exception) {
        super(httpStatus, exception);
    }
}