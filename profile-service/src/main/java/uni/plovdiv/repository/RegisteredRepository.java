package uni.plovdiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uni.plovdiv.models.Registered;

import java.util.Optional;


public interface RegisteredRepository extends JpaRepository<Registered, Long>, JpaSpecificationExecutor<Registered> {

    /**
     * @param pageable
     * @return
     */
    Page<Registered> findAll(Pageable pageable);

    Optional<Registered> findByEmail(String email);

    Registered findByEmailAndIdIsNot(String emailAddress, Long id);

}
