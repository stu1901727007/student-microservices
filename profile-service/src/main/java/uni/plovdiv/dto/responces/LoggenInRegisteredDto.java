package uni.plovdiv.dto.responces;

import lombok.Getter;
import lombok.Setter;
import uni.plovdiv.models.RolesRegistered;

import java.io.Serializable;
import java.util.Collection;

@Setter
@Getter
public class LoggenInRegisteredDto implements Serializable {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    private Collection<RolesRegistered> roles;
}
