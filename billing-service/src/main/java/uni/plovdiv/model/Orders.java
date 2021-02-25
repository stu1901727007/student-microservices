package uni.plovdiv.model;

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
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "applications_id")
    private Long applicationsId;

    @Column(name = "applications_registered_id")
    private Long applicationsRegisteredId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "total")
    private Double total;

    @Column(name = "status")
    private Byte status;

    @Column(name = "payment_method")
    private Byte paymentMethod;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
