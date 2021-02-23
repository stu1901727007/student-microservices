package uni.plovdiv.dto.responces;

import lombok.Getter;
import lombok.Setter;

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
