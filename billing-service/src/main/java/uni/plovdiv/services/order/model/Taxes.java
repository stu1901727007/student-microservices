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
@Table(name = "taxes")
public class Taxes implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private Byte title;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private Byte type;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;
}
