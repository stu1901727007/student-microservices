package uni.plovdiv.dto.responces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JSONResponseDto implements Serializable {
    private int code;
    private HttpStatus status;
    private String error;
    private Map<String, String> errors;
    private Map<String, Object> data;

    public JSONResponseDto() {
    }

    public void setStatus(HttpStatus status) {
        this.code = status.value();
        this.status = status;
    }

    public JSONResponseDto prepareErrors(BindingResult bindingResult) throws NullPointerException {

        this.errors = new HashMap<String, String>();

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                this.errors.put(error.getField(), error.getDefaultMessage());
            }
        }

        return this;
    }
}

