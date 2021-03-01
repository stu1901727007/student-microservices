package uni.plovdiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uni.plovdiv.models.FrontUser;

import java.util.Optional;


public interface FrontUserRepository extends JpaRepository<FrontUser, Long>, JpaSpecificationExecutor<FrontUser> {

    /**
     * @param pageable
     * @return
     */
    Page<FrontUser> findAll(Pageable pageable);

    Optional<FrontUser> findByEmail(String email);

    FrontUser findByEmailAndIdIsNot(String emailAddress, Long id);

}
