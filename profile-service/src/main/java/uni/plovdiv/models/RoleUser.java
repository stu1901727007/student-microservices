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
@Table(name = "role_user")
public class RoleUser implements Serializable {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "users_id")
    private Long usersId;

    @Id
    @Column(name = "user_type")
    private String userType;
}
