package uni.plovdiv.dto;

import lombok.Getter;
import lombok.Setter;
import uni.plovdiv.models.RolesFrontUser;

import java.io.Serializable;
import java.util.Collection;

@Setter
@Getter
public class FrontUserLoggedInDto implements Serializable {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    private Collection<RolesFrontUser> roles;
}
