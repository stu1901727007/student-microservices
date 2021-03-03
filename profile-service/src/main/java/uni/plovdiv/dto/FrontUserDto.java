package uni.plovdiv.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import uni.plovdiv.models.RolesFrontUser;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class FrontUserDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String pin;
    private String fn;
    private String email;
    private java.sql.Timestamp emailVerifiedAt;
    private Byte active;
    private Collection<RolesFrontUser> roles;
}
