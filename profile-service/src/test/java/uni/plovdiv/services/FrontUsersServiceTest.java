package uni.plovdiv.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;
import uni.plovdiv.dto.FrontUserLoggedInDto;
import uni.plovdiv.models.FrontUser;
import uni.plovdiv.models.RolesFrontUser;
import uni.plovdiv.repository.FrontUserRepository;
import uni.plovdiv.repository.RoleFrontUserRepository;
import uni.plovdiv.requests.FrontUserDto;
import uni.plovdiv.requests.FrontUserSignupDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 *
 */
class FrontUsersServiceTest {

    private FrontUserRepository frontUserRepository;
    private RoleFrontUserRepository roleFrontUserRepository;
    private FrontUsersService frontUsersService;
    private Validator validator;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        this.modelMapper = new ModelMapper();

        this.frontUserRepository = mock(FrontUserRepository.class);
        this.roleFrontUserRepository = mock(RoleFrontUserRepository.class);

        FrontUser frontUser = new FrontUser();
        frontUser.setId(1L);
        frontUser.setFirstName("Vitali");
        frontUser.setLastName("Atias");
        frontUser.setEmail("vit@vit.bg");
        frontUser.setPassword("123123");
        List<FrontUser> frontUsers = Arrays.asList(frontUser);

        doReturn(frontUsers).when(this.frontUserRepository).findAll();
        doReturn(Optional.of(frontUser)).when(this.frontUserRepository).findById(1L);
        doReturn(Optional.of(frontUser)).when(this.frontUserRepository).findByEmail("vit@vit.bg");


        RolesFrontUser roles = new RolesFrontUser();
        roles.setName("candidate");
        roles.setDisplayName("Candidates");

        doReturn(Optional.of(roles)).when(this.roleFrontUserRepository).findById(1L);

        this.frontUsersService = new FrontUsersService(this.frontUserRepository, this.roleFrontUserRepository);
    }

    @Test
    void testGetFrontUserById() {
        FrontUser frontUser = this.frontUsersService.getById(1L);
        assertEquals("Vitali", frontUser.getFirstName());
    }


    @Test
    void testGetFrontUserByIdNull() {
        assertThrows(NullPointerException.class, ()-> this.frontUsersService.getById(2L));
    }


    @Test
    void testGetFrontUserByEmail() {
        FrontUser frontUser = this.frontUsersService.getByEmail("vit@vit.bg");
        assertEquals("Vitali", frontUser.getFirstName());
    }

    @Test
    void testGetFrontUserByEmailNull() {
        assertThrows(NullPointerException.class, ()-> this.frontUsersService.getByEmail("vit1@vit.bg"));
    }

    /**
     * Streams arguments to test front users creations process
     *
     * @return Stream<Arguments>
     */
    private static Stream<Arguments> NotValidFrontUserDetails() {
        return Stream.of(
                Arguments.of(null, null, null, null),
                Arguments.of("a", null, null, null),
                Arguments.of("a", "b", null, null),
                Arguments.of("a", "b", "v", null),
                Arguments.of("a", "b", "v@vf.bg", null),
                Arguments.of(null, "b", null, "12123")
        );
    }

    /**
     * Tests not valid front user details
     *
     * @param firstName String representation of first name
     * @param lastName String representation of last name
     * @param email String representation of email
     * @param password String representation of password
     */
    @ParameterizedTest
    @MethodSource("NotValidFrontUserDetails")
    void testCreateFrontUserWithInvalidDetails(String firstName, String lastName, String email, String password) {

        FrontUserDto frontUserDto = new FrontUserDto();

        frontUserDto.setFirstName(firstName);
        frontUserDto.setLastName(lastName);
        frontUserDto.setEmail(email);
        frontUserDto.setPassword(password);

        Set<ConstraintViolation<FrontUserDto>> violations = this.validator.validate(frontUserDto);
        assertFalse(((Set<?>) violations).isEmpty());
        //assertThrows(IllegalArgumentException.class, ()-> this.frontUsersService.create(frontUserDto));
    }

    @Test
    void testCreateFrontUserWithValidDetails() {

        FrontUserDto frontUserDto = new FrontUserDto();

        frontUserDto.setFirstName("Stefan");
        frontUserDto.setLastName("Ivanov");
        frontUserDto.setEmail("stefan@gmail.com");
        frontUserDto.setPassword("123123");

        assertDoesNotThrow(()-> this.frontUsersService.create(frontUserDto));
    }




    /**
     * Tests not valid front user details
     *
     * @param firstName String representation of first name
     * @param lastName String representation of last name
     * @param email String representation of email
     * @param password String representation of password
     */
    @ParameterizedTest
    @MethodSource("NotValidFrontUserDetails")
    void testCreateFrontUserSignUpWithInvalidDetails(String firstName, String lastName, String email, String password) {

        FrontUserSignupDto frontUserSignupDto = new FrontUserSignupDto();
        frontUserSignupDto.setFirstName(firstName);
        frontUserSignupDto.setLastName(lastName);
        frontUserSignupDto.setEmail(email);
        frontUserSignupDto.setPassword(password);
        frontUserSignupDto.setRoles(Arrays.asList(this.roleFrontUserRepository.findById(1L).get()));

        Set<ConstraintViolation<FrontUserSignupDto>> violations = this.validator.validate(frontUserSignupDto);
        assertFalse(((Set<?>) violations).isEmpty());
    }

    @Test
    void testCreateFrontUserSignUpWithValidDetails() {

        FrontUserSignupDto frontUserSignupDto = new FrontUserSignupDto();

        frontUserSignupDto.setFirstName("Stefan");
        frontUserSignupDto.setLastName("Ivanov");
        frontUserSignupDto.setEmail("stefan@gmail.com");
        frontUserSignupDto.setPassword("123123");
        frontUserSignupDto.setRoles(Arrays.asList(this.roleFrontUserRepository.findById(1L).get()));

        Set<ConstraintViolation<FrontUserSignupDto>> violations = this.validator.validate(frontUserSignupDto);
        assertDoesNotThrow(()-> this.frontUsersService.signup(frontUserSignupDto));
    }


    /**
     * Tests front user deletion
     */
    @Test
    void testDeleteFrontUser() {
        FrontUser frontUser = this.frontUsersService.getById(1L);
        assertTrue(this.frontUsersService.delete(frontUser));
    }


    @Test
    void testUpdateFrontUser() {

        FrontUser frontUser = this.frontUsersService.getById(1L);

        FrontUserDto frontUserDto = modelMapper.map(frontUser, FrontUserDto.class);

        frontUserDto.setFirstName("Stefan1");
        frontUserDto.setLastName("Ivanov2");

        FrontUser frontUser1 = this.frontUsersService.update(frontUserDto, frontUser);

        assertEquals("Stefan1", frontUser1.getFirstName());

    }

}


