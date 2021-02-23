package uni.plovdiv.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
     *
     * @param registeredRepository
     * @param registeredService
     */
    public RegisteredController(RegisteredRepository registeredRepository, RegisteredService registeredService) {
        this.registeredRepository = registeredRepository;
        this.registeredService = registeredService;
    }

    /**
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

                LoggenInRegisteredDto loggenInRegisteredDto = modelMapper.map(rsRegistered, LoggenInRegisteredDto.class);

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user", loggenInRegisteredDto);

                response.setData(data);
                httpStatus = HttpStatus.OK;
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


    @DeleteMapping("{registred_id}")
    public ResponseEntity<JSONResponseDto> deleteRegistered(
            @PathVariable(value = "registred_id") long registred_id,
            JSONResponseDto response,
            HttpStatus httpStatus
    ) {

        Optional<Registered> registered = registeredRepository.findById(registred_id);

        if (registered.isPresent()) {

            Registered rsRegistered = registered.get();

            if( this.registeredService.delete(rsRegistered) ) {
                httpStatus = HttpStatus.OK;
            }
            else
            {
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


}
