package uni.plovdiv.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RegisteredDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String pin;
    private String fn;
    private String email;
    private java.sql.Timestamp emailVerifiedAt;
    private Byte active;
    private Collection<RolesRegistered> roles;
}
