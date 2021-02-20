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
@Table(name = "forms")
public class Form implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "text")
    private Byte text;

    @Id
    @Column(name = "taxes_id")
    private Long taxesId;

    @Column(name = "enable_specialities")
    private Byte enableSpecialities;

    @Column(name = "max_time_send")
    private Byte maxTimeSend;

    @Column(name = "enable_exams")
    private Byte enableExams;

    @Column(name = "date_start")
    private java.sql.Date dateStart;

    @Column(name = "date_end")
    private java.sql.Date dateEnd;

    @Column(name = "active")
    private Byte active;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;
}
