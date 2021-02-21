package uni.plovdiv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orders_id")
    private Long ordersId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private Byte status;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

}
