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
@Table(name = "application_form_elemens")
public class ApplicationFormElemen implements Serializable {
    @Id
    @Column(name = "applications_id")
    private Long applicationsId;

    @Id
    @Column(name = "form_elements_forms_id")
    private Long formElementsFormsId;

    @Id
    @Column(name = "form_builder_elements_id")
    private Long formBuilderElementsId;

    @Column(name = "variables")
    private String variables;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;
}
