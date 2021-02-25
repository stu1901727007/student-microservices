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
@Table(name = "applications")
public class Application implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private Byte paymentMethod;

    @Column(name = "details")
    private String details;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
