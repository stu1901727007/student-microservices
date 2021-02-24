package uni.plovdiv.dto.requests;

import lombok.Data;
import lombok.experimental.Accessors;
import uni.plovdiv.models.RolesRegistered;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Accessors(chain = true)
public class SignupDto {

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min=6, max = 80)
    private String password;

    private Collection<RolesRegistered> roles;
}
