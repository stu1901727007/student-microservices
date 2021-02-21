package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "registered")
public class Registered implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(nullable = false)
    @Size(max = 50)
    private String middleName;

    @Column(nullable = false)
    @Size(max = 50)
    private String lastName;

    @Column(name = "pin") // or pif
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

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 80)
    private String password;

    @Column(name = "active")
    private Byte active;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

}
