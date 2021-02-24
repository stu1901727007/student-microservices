package uni.plovdiv.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uni.plovdiv.dto.requests.RegisteredDto;
import uni.plovdiv.dto.requests.SignupDto;
import uni.plovdiv.models.Registered;
import uni.plovdiv.models.RolesRegistered;
import uni.plovdiv.repository.RegisteredRepository;
import uni.plovdiv.repository.RoleRegistredRepository;
import uni.plovdiv.services.interfaces.RegisteredServiceInteface;
import uni.plovdiv.utils.BCryptUtils;

import java.util.Arrays;
import java.util.Optional;

@Component
public class RegisteredService implements RegisteredServiceInteface {

    RegisteredRepository registeredRepository;
    RoleRegistredRepository roleRegistredRepository;
    ModelMapper modelMapper = new ModelMapper();
    BCryptUtils bCryptUtils = new BCryptUtils();

    /**
     *
     * @param registeredRepository
     * @param roleRegistredRepository
     */
    public RegisteredService(RegisteredRepository registeredRepository, RoleRegistredRepository roleRegistredRepository) {
        this.registeredRepository = registeredRepository;
        this.roleRegistredRepository = roleRegistredRepository;
    }

    /**
     * @param registeredDto
     * @return
     */
    public Registered create(RegisteredDto registeredDto) {

        Registered registered = new Registered();
        return _updateRecord(registeredDto, registered);
    }

    /**
     * @param signupDto
     * @return
     */
    public Registered signup(SignupDto signupDto) {

        RolesRegistered role = new RolesRegistered();
        Registered registered = new Registered();

        Optional<RolesRegistered> rsRole = roleRegistredRepository.findById(1L);
        if (rsRole.isPresent()) {
            registered
                    .setFirstName(signupDto.getFirstName())
                    .setLastName(signupDto.getLastName())
                    .setEmail(signupDto.getEmail())
                    .setPassword(bCryptUtils.bcryptEncryptor(signupDto.getPassword()))
                    .setRoles(Arrays.asList(rsRole.get()));

            this.registeredRepository.save(registered);
        }

        return registered;
    }

    /**
     * @param registeredDto
     * @param registered
     * @return
     */
    public Registered update(RegisteredDto registeredDto, Registered registered) {

        return _updateRecord(registeredDto, registered);
    }

    /**
     * @param registeredDto
     * @param registered
     * @return
     */
    private Registered _updateRecord(RegisteredDto registeredDto, Registered registered) {

        this.registeredRepository.save(registered);

        return registered;
    }

    /**
     * @param registered
     * @return
     */
    public Boolean delete(Registered registered) {

        this.registeredRepository.delete(registered);

        return true;
    }
}
