package uni.plovdiv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import uni.plovdiv.models.RolesFrontUser;
import uni.plovdiv.repository.RoleFrontUserRepository;

import java.util.Optional;

@SpringBootApplication
@EnableFeignClients
public class ProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }


    @Bean
    CommandLineRunner init(RoleFrontUserRepository roleFrontUserRepository) {

        return args -> {

            Optional<RolesFrontUser> role = roleFrontUserRepository.findById(1L);
            if (!role.isPresent()) {
                RolesFrontUser roles = new RolesFrontUser();
                roles.setName("candidate");
                roles.setDisplayName("Candidates");
                roleFrontUserRepository.save(roles);
            }
        };
    }


}
