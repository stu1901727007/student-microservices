package uni.plovdiv.controller.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

@Data
public class ErrorMessage implements Serializable {

    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ErrorMessage(HttpStatus status, String message, Map<String, String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

//    public ErrorMessage(HttpStatus status, String message, String error) {
//        super();
//        this.status = status;
//        this.message = message;
//        errors = Arrays.asList(error);
//    }
}