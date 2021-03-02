package uni.plovdiv.controller.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

@Data
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ErrorMessage(HttpStatus status, String message, Map<String, String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}