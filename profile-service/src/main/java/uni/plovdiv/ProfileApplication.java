package uni.plovdiv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import uni.plovdiv.models.RolesRegistered;
import uni.plovdiv.repository.RoleRegistredRepository;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@EnableFeignClients
public class ProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }


    @Bean
    CommandLineRunner init(RoleRegistredRepository roleRegistredRepository) {

        return args -> {

            Optional<RolesRegistered> role = roleRegistredRepository.findById(1L);
            if (role.isEmpty()) {
                RolesRegistered roles = new RolesRegistered();
                roles.setName("candidate");
                roles.setDisplayName("Candidates");
                roleRegistredRepository.save(roles);
            }

//			User admin = userRepository.findByEmail("admin@local.com");
//			if (admin == null) {
//				admin = new User()
//						.setEmail("admin@local.com")
//						.setPassword(bCryptPasswordEncoder.encode("123123"))
//						.setFirstName("Vitali")
//						.setLastName("Atias")
//						.setMobileNumber("111111")
//						.setRoles(Arrays.asList(adminRole));
//				userRepository.save(admin);
//			}
        };
    }


}
