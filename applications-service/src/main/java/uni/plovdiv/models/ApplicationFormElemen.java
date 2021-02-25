package uni.plovdiv.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "application_form_elemens")
public class ApplicationFormElemen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applications_id")
    private Integer id;


    @Column(name = "applications_id")
    private Long applicationsId;


    @Column(name = "form_elements_forms_id")
    private Long formElementsFormsId;


    @Column(name = "form_builder_elements_id")
    private Long formBuilderElementsId;

    @Column(name = "variables")
    private String variables;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
