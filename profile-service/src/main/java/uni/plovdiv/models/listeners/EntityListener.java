package uni.plovdiv.models.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class EntityListener {

    @PrePersist
    private void beforeAnyCreate(Object o) {
    }

    @PreUpdate
    private void beforeAnyUpdate(Object o) {
    }
}
