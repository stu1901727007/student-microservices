package uni.plovdiv.services.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "applications_id")
    private Long applicationsId;

    @Id
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
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;
}
