package uni.plovdiv.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uni.plovdiv.dto.requests.RegisteredDto;
import uni.plovdiv.dto.requests.SignupDto;
import uni.plovdiv.dto.responces.JSONResponseDto;
import uni.plovdiv.dto.responces.LoggenInRegisteredDto;
import uni.plovdiv.models.Registered;
import uni.plovdiv.repository.RegisteredRepository;
import uni.plovdiv.dto.requests.LoginRegisteredDto;
import uni.plovdiv.services.RegisteredService;
import uni.plovdiv.utils.BCryptUtils;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/registered")
public class RegisteredController {

    RegisteredRepository registeredRepository;
    RegisteredService registeredService;
    BCryptUtils bCryptUtils = new BCryptUtils();
    ModelMapper modelMapper = new ModelMapper();

    /**
     * Constructor
     *
     * @param registeredRepository
     * @param registeredService
     */
    public RegisteredController(RegisteredRepository registeredRepository, RegisteredService registeredService) {
        this.registeredRepository = registeredRepository;
        this.registeredService = registeredService;
    }

    /**
     * Login all registered users
     *
     * @param loginRegisteredDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<JSONResponseDto> loginUser(
            @Valid LoginRegisteredDto loginRegisteredDto,
            BindingResult bindingResult,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        if (!bindingResult.hasErrors()) {

            Optional<Registered> registered = registeredRepository.findByEmail(loginRegisteredDto.getEmail());

            if (registered.isPresent()) {

                Registered rsRegistered = registered.get();

                if (bCryptUtils.doPasswordsMatch(loginRegisteredDto.getPassword(), rsRegistered.getPassword())) {

                    LoggenInRegisteredDto loggenInRegisteredDto = modelMapper.map(rsRegistered, LoggenInRegisteredDto.class);

                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user", loggenInRegisteredDto);

                    response.setData(data);
                    httpStatus = HttpStatus.OK;
                } else {
                    httpStatus = HttpStatus.UNAUTHORIZED;
                }
            } else {
                httpStatus = HttpStatus.UNAUTHORIZED;
            }

        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.prepareErrors(bindingResult);
            response.setError("Form data not valid!");
        }

        if (httpStatus == HttpStatus.UNAUTHORIZED) {
            response.setError("Not valid credentials");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Signup new users
     *
     * @param signupDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<JSONResponseDto> loginUser(
            @Valid SignupDto signupDto,
            BindingResult bindingResult,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        if (!bindingResult.hasErrors()) {

            Optional<Registered> registered = registeredRepository.findByEmail(signupDto.getEmail());

            if (!registered.isPresent()) {

                Registered rsRegistered = this.registeredService.signup(signupDto);

                if (rsRegistered != null) {
                    LoggenInRegisteredDto loggenInRegisteredDto = modelMapper.map(rsRegistered, LoggenInRegisteredDto.class);

                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user", loggenInRegisteredDto);

                    response.setData(data);
                    httpStatus = HttpStatus.OK;
                } else {
                    response.setError("There is a problem with your registration!");
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                response.setError("Email already used!");
            }

        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.prepareErrors(bindingResult);
            response.setError("Form data not valid!");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     *
     * @param registred_id
     * @param response
     * @param httpStatus
     * @return
     */
    @GetMapping("{registred_id}")
    public ResponseEntity<JSONResponseDto> select(
            @PathVariable(value = "registred_id") long registred_id,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        Optional<Registered> registered = registeredRepository.findById(registred_id);

        if (registered.isPresent()) {

            Registered rsRegistered = registered.get();
            RegisteredDto registeredDto = this.modelMapper.map(rsRegistered, RegisteredDto.class);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("user", registeredDto);
            response.setData(data);

            httpStatus = HttpStatus.OK;

        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setError("Not found!");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Delete single registered user
     *
     * @param registred_id
     * @param response
     * @param httpStatus
     * @return
     */
    @DeleteMapping("{registred_id}")
    public ResponseEntity<JSONResponseDto> delete(
            @PathVariable(value = "registred_id") long registred_id,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        Optional<Registered> registered = registeredRepository.findById(registred_id);

        if (registered.isPresent()) {

            Registered rsRegistered = registered.get();

            if (this.registeredService.delete(rsRegistered)) {
                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                response.setError("There is a problem!");
            }
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setError("Not found!");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * @param registeredDto
     * @param response
     * @param httpStatus
     * @return
     */
    @PostMapping
    public ResponseEntity<JSONResponseDto> create(
            @Valid RegisteredDto registeredDto,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        Optional<Registered> registered = registeredRepository.findByEmail(registeredDto.getEmail());

        if (registered.isEmpty()) {

            Registered newRegistered = this.registeredService.create(registeredDto);

            if (newRegistered != null) {

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user", registeredDto);
                response.setData(data);

                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                response.setError("There is a problem!");
            }
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setError("Email already used!");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * @param registred_id
     * @param registeredDto
     * @param response
     * @param httpStatus
     * @return
     */
    @PutMapping("{registred_id}")
    public ResponseEntity<JSONResponseDto> update(
            @PathVariable(value = "registred_id") long registred_id,
            @Valid RegisteredDto registeredDto,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        boolean emailValid = true;

        Optional<Registered> registered = registeredRepository.findById(registred_id);

        if (registered.isPresent()) {

            Registered rsRegistered = registered.get();

            if (rsRegistered.getEmail() != registeredDto.getEmail()) {
                Optional<Registered> checkEmail = Optional.ofNullable(registeredRepository.findByEmailAndIdIsNot(registeredDto.getEmail(), rsRegistered.getId()));

                if (checkEmail.isEmpty()) {
                    emailValid = true;
                } else {
                    emailValid = false;
                }
            }

            if (emailValid) {
                rsRegistered = this.registeredService.update(registeredDto, registered.get());

                if (rsRegistered != null) {

                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user", registeredDto);
                    response.setData(data);
                    httpStatus = HttpStatus.OK;

                } else {
                    httpStatus = HttpStatus.BAD_REQUEST;
                    response.setError("There is a problem!");
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                response.setError("Email already used!");
            }

        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setError("Not found!");
        }

        response.setStatus(httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
