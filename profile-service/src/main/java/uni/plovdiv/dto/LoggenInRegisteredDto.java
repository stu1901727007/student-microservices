package uni.plovdiv.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class LoggenInRegisteredDto implements Serializable {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

}
