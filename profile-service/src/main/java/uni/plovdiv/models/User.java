package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Byte active;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    //@ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "role_user",
//            joinColumns = {@JoinColumn(name = "users_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private Collection<Role> roles;

    public String getFullName() {
        return this.firstName != null ? this.firstName.concat(" ").concat(this.lastName) : "";
    }


}
