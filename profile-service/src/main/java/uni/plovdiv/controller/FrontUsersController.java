package uni.plovdiv.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uni.plovdiv.dto.FrontUserLoggedInDto;
import uni.plovdiv.models.FrontUser;
import uni.plovdiv.repository.FrontUserRepository;
import uni.plovdiv.requests.FrontUserDto;
import uni.plovdiv.requests.FrontUserSignupDto;
import uni.plovdiv.requests.LoginFrontUserDto;
import uni.plovdiv.services.FrontUsersService;
import uni.plovdiv.utils.BCryptUtils;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/front-user")
public class FrontUsersController {

    final static String EMAIL_ALREADY_USED = "Email already used!";
    final static String NOT_FOUND = "Not found";
    final static String UNAUTHORIZED = "Unauthorized";

    FrontUserRepository frontUserRepository;
    FrontUsersService frontUserService;
    BCryptUtils bCryptUtils = new BCryptUtils();
    ModelMapper modelMapper = new ModelMapper();

    /**
     * Constructor
     *
     * @param frontUserRepository
     * @param frontUserService
     */
    public FrontUsersController(FrontUserRepository frontUserRepository, FrontUsersService frontUserService) {
        this.frontUserRepository = frontUserRepository;
        this.frontUserService = frontUserService;
    }

    /**
     * Login all front users
     *
     * @param loginFrontUserDto
     * @return
     */

    @PostMapping("/login")
    public ResponseEntity<FrontUserLoggedInDto> loginUser(
            @Valid @ModelAttribute LoginFrontUserDto loginFrontUserDto,
            BindingResult bindingResult

    ) throws ResponseStatusException, BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        FrontUser frontUser = frontUserService.getByEmail(loginFrontUserDto.getEmail());

        if (frontUser != null) {

            if (Boolean.TRUE.equals(bCryptUtils.doPasswordsMatch(loginFrontUserDto.getPassword(), frontUser.getPassword()))) {

                FrontUserLoggedInDto frontUserLoggedInDto = modelMapper.map(frontUser, FrontUserLoggedInDto.class);
                return new ResponseEntity<>(frontUserLoggedInDto, HttpStatus.OK);
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
    }

    /**
     * Signup new users
     *
     * @param frontUserSignupDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<FrontUserLoggedInDto> loginUser(
            @Valid @ModelAttribute FrontUserSignupDto frontUserSignupDto,
            BindingResult bindingResult
    ) throws ResponseStatusException, BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        FrontUser checkUserByEmail = frontUserService.getByEmail(frontUserSignupDto.getEmail());

        if (checkUserByEmail == null) {

            FrontUser rsFrontUser = this.frontUserService.signup(frontUserSignupDto);

            if (rsFrontUser != null) {
                FrontUserLoggedInDto frontUserLoggedInDto = modelMapper.map(rsFrontUser, FrontUserLoggedInDto.class);
                return new ResponseEntity<>(frontUserLoggedInDto, HttpStatus.CREATED);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMAIL_ALREADY_USED);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with your registration");
    }

    /**
     * @param frontUserId
     * @return
     */
    @GetMapping("{frontUserId}")
    public ResponseEntity<FrontUserDto> select(
            @PathVariable(value = "frontUserId") long frontUserId
    ) throws ResponseStatusException {

        FrontUser frontUser = frontUserService.getById(frontUserId);

        if (frontUser != null) {
            FrontUserDto frontUserDto = this.modelMapper.map(frontUser, FrontUserDto.class);
            return new ResponseEntity<>(frontUserDto, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND);
    }

    /**
     * Delete single frontUser user
     *
     * @param frontUserId
     * @return
     */
    @DeleteMapping("{frontUserId}")
    public ResponseEntity<FrontUserDto> delete(
            @PathVariable(value = "frontUserId") long frontUserId
    ) {
        FrontUser frontUser = frontUserService.getById(frontUserId);

        if (Boolean.TRUE.equals(this.frontUserService.delete(frontUser))) {

            FrontUserDto frontUserDto = this.modelMapper.map(frontUser, FrontUserDto.class);
            return new ResponseEntity<>(frontUserDto, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND);
    }

    /**
     * @param frontUserDto
     * @return
     */
    @PostMapping
    public ResponseEntity<FrontUserDto> create(
            @Valid FrontUserDto frontUserDto
    ) throws ResponseStatusException {

        FrontUser checkUserByEmail = frontUserService.getByEmail(frontUserDto.getEmail());

        if (checkUserByEmail == null) {

            FrontUser newFrontUser = this.frontUserService.create(frontUserDto);

            frontUserDto = this.modelMapper.map(newFrontUser, FrontUserDto.class);

            if (newFrontUser != null) {
                return new ResponseEntity<>(frontUserDto, HttpStatus.CREATED);
            }
        } else {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMAIL_ALREADY_USED);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem to create new record");
    }

    /**
     * @param frontUserId
     * @param frontUserDto
     * @return
     */
    @PutMapping("{frontUserId}")
    public ResponseEntity<FrontUserDto> update(
            @PathVariable(value = "frontUserId") long frontUserId,
            @Valid FrontUserDto frontUserDto
    ) {

        boolean emailValid = true;

        FrontUser frontUser = frontUserService.getById(frontUserId);

        if (frontUser != null) {

            if ( !frontUser.getEmail().equals(frontUserDto.getEmail())) {
                Optional<FrontUser> checkEmail = Optional.ofNullable(frontUserRepository.findByEmailAndIdIsNot(frontUserDto.getEmail(), frontUser.getId()));

                if (!checkEmail.isPresent()) {
                    emailValid = true;
                } else {
                    emailValid = false;
                }
            }

            if (emailValid) {
                frontUser = this.frontUserService.update(frontUserDto, frontUser);

                if (frontUser != null) {
                    return new ResponseEntity<>(frontUserDto, HttpStatus.OK);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMAIL_ALREADY_USED);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem to create new record");
    }
}
