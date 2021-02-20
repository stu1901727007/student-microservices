package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "password_resets")
public class PasswordReset implements Serializable {
    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

}
