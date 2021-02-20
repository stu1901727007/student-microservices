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
@Table(name = "form_builder_templates")
public class FormBuilderTemplate implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "title")
    private Byte title;

    @Column(name = "details")
    private Byte details;

    @Column(name = "load_module")
    private String loadModule;

    @Column(name = "is_multiple_upload")
    private Byte isMultipleUpload;

    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

}
