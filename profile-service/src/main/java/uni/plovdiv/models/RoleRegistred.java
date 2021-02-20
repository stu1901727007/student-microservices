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
@Table(name = "role_registred")
public class RoleRegistred implements Serializable {
    @Id
    @Column(name = "registered_id")
    private Long registeredId;

    @Id
    @Column(name = "roles_registered_id")
    private Long rolesRegisteredId;
}
