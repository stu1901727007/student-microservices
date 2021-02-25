package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "form_role_registred")
public class FormRoleRegistred implements Serializable {

    @Column(name = "forms_id")
    private Long formsId;

    @Column(name = "roles_registered_id")
    private Long rolesRegisteredId;
}
