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
@Table(name = "applications")
public class Application implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Id
    @Column(name = "forms_id")
    private Long formsId;

    @Id
    @Column(name = "registered_id")
    private Long registeredId;

    @Column(name = "status")
    private Byte status;

    @Column(name = "payment_method")
    private Byte paymentMethod;

    @Column(name = "details")
    private String details;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;
}
