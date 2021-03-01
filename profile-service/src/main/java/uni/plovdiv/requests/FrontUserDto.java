package uni.plovdiv.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import uni.plovdiv.models.RolesFrontUser;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Accessors(chain = true)
public class FrontUserDto {

    @Column(nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(nullable = false)
    @Size(max = 50)
    private String middleName;

    @Column(nullable = false)
    @Size(max = 50)
    private String lastName;

    @Column(name = "pin") //or pif
    @Size(max = 10)
    private String pin;

    @Column(name = "fn")
    @Size(max = 10)
    private String fn;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @Column(name = "email_verified_at")
    private java.sql.Timestamp emailVerifiedAt;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 80)
    private String password;

    @Column(name = "active")
    private Byte active;

    private Collection<RolesFrontUser> roles;
}
