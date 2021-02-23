package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import uni.plovdiv.models.interfaces.SoftDelete;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "registered")
@SQLDelete(sql = "UPDATE registered SET deleted_at=NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Registered implements Serializable, SoftDelete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Column
    @Size(max = 50)
    private String middleName;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Column(name = "pin", unique = true) // or pif
    @Size(max = 10)
    private String pin;

    @Column(name = "fn", unique = true)
    @Size(max = 10)
    private String fn;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @Column(name = "email_verified_at")
    private Date emailVerifiedAt;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 6, max = 80)
    private String password;

    @Column(name = "active")
    private Byte active;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public Registered touchDelete() {

        if (this instanceof SoftDelete) {
            this.setDeletedAt(new Date());
        }

        return this;
    }
}
