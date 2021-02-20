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
@Table(name = "form_role_registred")
public class FormRoleRegistred implements Serializable {
    @Id
    @Column(name = "forms_id")
    private Long formsId;

    @Id
    @Column(name = "roles_registered_id")
    private Long rolesRegisteredId;
}
