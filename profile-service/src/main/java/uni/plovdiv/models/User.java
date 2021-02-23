package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uni.plovdiv.models.interfaces.SoftDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements Serializable, SoftDelete {
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
    private Date deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    //@ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "role_user",
//            joinColumns = {@JoinColumn(name = "users_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private Collection<Role> roles;

    public String getFullName() {
        return this.firstName != null ? this.firstName.concat(" ").concat(this.lastName) : "";
    }

    public User touchDelete() {
        this.setDeletedAt(new Date());
        return this;
    }
}
