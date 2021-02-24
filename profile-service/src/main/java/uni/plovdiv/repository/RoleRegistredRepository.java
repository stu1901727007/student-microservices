package uni.plovdiv.repository;

import org.springframework.data.repository.CrudRepository;
import uni.plovdiv.models.RolesRegistered;

public interface RoleRegistredRepository extends CrudRepository<RolesRegistered, Long> {

    //RolesRegistered findByRole(int role);
}
