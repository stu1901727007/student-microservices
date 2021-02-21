package uni.plovdiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uni.plovdiv.models.Registered;

import java.util.Optional;


public interface RegisteredRepository extends JpaRepository<Registered, Long> {

    /**
     * @param pageable
     * @return
     */
    Page<Registered> findAll(Pageable pageable);

    Optional<Registered> findByEmail(String email);
}
