package uni.plovdiv.requests;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class LoginRegisteredRequest {

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 80)
    private String password;
}
