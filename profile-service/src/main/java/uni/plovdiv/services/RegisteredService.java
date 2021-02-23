package uni.plovdiv.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uni.plovdiv.dto.requests.RegisteredDto;
import uni.plovdiv.dto.requests.SignupDto;
import uni.plovdiv.models.Registered;
import uni.plovdiv.repository.RegisteredRepository;
import uni.plovdiv.services.interfaces.RegisteredServiceInteface;
import uni.plovdiv.utils.BCryptUtils;

@Component
public class RegisteredService implements RegisteredServiceInteface {

    RegisteredRepository registeredRepository;
    ModelMapper modelMapper = new ModelMapper();
    BCryptUtils bCryptUtils = new BCryptUtils();

    public RegisteredService(RegisteredRepository registeredRepository) {
        this.registeredRepository = registeredRepository;
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

        Registered registered = new Registered();
        registered.setFirstName(signupDto.getFirstName());
        registered.setLastName(signupDto.getLastName());
        registered.setEmail(signupDto.getEmail());
        registered.setPassword(bCryptUtils.bcryptEncryptor(signupDto.getPassword()));
        this.registeredRepository.save(registered);

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

        registered.touchDelete();
        this.registeredRepository.save(registered);

        return true;
    }
}
