package uni.plovdiv.services;

import org.springframework.stereotype.Component;
import uni.plovdiv.models.FrontUser;
import uni.plovdiv.models.RolesFrontUser;
import uni.plovdiv.repository.FrontUserRepository;
import uni.plovdiv.repository.RoleFrontUserRepository;
import uni.plovdiv.requests.FrontUserDto;
import uni.plovdiv.requests.FrontUserSignupDto;
import uni.plovdiv.services.interfaces.FrontUsersServiceInteface;
import uni.plovdiv.utils.BCryptUtils;

import java.util.Arrays;
import java.util.Optional;

@Component
public class FrontUsersService implements FrontUsersServiceInteface {

    final BCryptUtils bCryptUtils = new BCryptUtils();

    FrontUserRepository frontUserRepository;
    RoleFrontUserRepository roleFrontUserRepository;

    /**
     *
     * @param frontUserRepository
     * @param roleFrontUserRepository
     */

    public FrontUsersService(FrontUserRepository frontUserRepository, RoleFrontUserRepository roleFrontUserRepository) {
        this.frontUserRepository = frontUserRepository;
        this.roleFrontUserRepository = roleFrontUserRepository;
    }

    /**
     *
     * @param frontUserId
     * @return
     */
    @Override
    public FrontUser getById(long frontUserId) {

        Optional<FrontUser> frontUser = frontUserRepository.findById(frontUserId);

        if (frontUser.isPresent()) {
            return frontUser.get();
        }

        return null;
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public FrontUser getByEmail(String email) {
        Optional<FrontUser> frontUser = frontUserRepository.findByEmail(email);

        if (frontUser.isPresent()) {
            return frontUser.get();
        }

        return null;
    }

    /**
     * @param frontUserDto
     * @return
     */
    @Override
    public FrontUser create(FrontUserDto frontUserDto) {

        FrontUser frontUser = new FrontUser();

        return _updateRecord(frontUserDto, frontUser);
    }

    /**
     * @param frontUserSignupDto
     * @return
     */
    @Override
    public FrontUser signup(FrontUserSignupDto frontUserSignupDto) {

        RolesFrontUser role = new RolesFrontUser();
        FrontUser frontUser = new FrontUser();

        Optional<RolesFrontUser> rsRole = roleFrontUserRepository.findById(1L);
        if (rsRole.isPresent()) {
            frontUser
                    .setFirstName(frontUserSignupDto.getFirstName())
                    .setLastName(frontUserSignupDto.getLastName())
                    .setEmail(frontUserSignupDto.getEmail())
                    .setPassword(bCryptUtils.bcryptEncryptor(frontUserSignupDto.getPassword()))
                    .setRoles(Arrays.asList(rsRole.get()));

            this.frontUserRepository.save(frontUser);
        }

        return frontUser;
    }

    /**
     * @param frontUserDto
     * @param frontUser
     * @return
     */
    @Override
    public FrontUser update(FrontUserDto frontUserDto, FrontUser frontUser) {

        return _updateRecord(frontUserDto, frontUser);
    }


    /**
     * @param frontUser
     * @return
     */
    @Override
    public Boolean delete(FrontUser frontUser) {

        this.frontUserRepository.delete(frontUser);

        return true;
    }

    /**
     * @param frontUserDto
     * @param frontUser
     * @return
     */
    private FrontUser _updateRecord(FrontUserDto frontUserDto, FrontUser frontUser) {

        Byte active = frontUserDto.getActive() == null ? 1 : frontUserDto.getActive();

        frontUser
                .setFirstName(frontUserDto.getFirstName())
                .setMiddleName(frontUserDto.getMiddleName())
                .setLastName(frontUserDto.getLastName())
                .setEmail(frontUserDto.getEmail())
                .setPin(frontUserDto.getPin())
                .setActive(active);

        if( !bCryptUtils.doPasswordsMatch(frontUserDto.getPassword(), frontUser.getPassword() ) )
        {
            frontUser.setPassword(bCryptUtils.bcryptEncryptor(frontUserDto.getPassword()));
        }

        this.frontUserRepository.save(frontUser);

        return frontUser;
    }

}
