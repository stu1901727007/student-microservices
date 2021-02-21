package uni.plovdiv.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.JSONResponseDto;
import uni.plovdiv.dto.LoggenInRegisteredDto;
import uni.plovdiv.models.Registered;
import uni.plovdiv.repository.RegisteredRepository;
import uni.plovdiv.requests.LoginRegisteredRequest;
import uni.plovdiv.utils.BCryptUtils;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/registered")
public class RegisteredController {

    RegisteredRepository registeredRepository;
    BCryptUtils bCryptUtils = new BCryptUtils();
    ModelMapper modelMapper = new ModelMapper();

    public RegisteredController(RegisteredRepository registeredRepository) {

        this.registeredRepository = registeredRepository;
        this.bCryptUtils = bCryptUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<JSONResponseDto> loginUser(
            @Valid LoginRegisteredRequest loginRegisteredRequest,
            BindingResult bindingResult
    ) {

        JSONResponseDto response = new JSONResponseDto();
        HttpStatus httpStatus;

        if (!bindingResult.hasErrors()) {

            Optional<Registered> registered = registeredRepository.findByEmail(loginRegisteredRequest.getEmail());

            if (registered.isPresent()) {

                Registered rsRegistered = registered.get();

                if (bCryptUtils.doPasswordsMatch(loginRegisteredRequest.getPassword(), rsRegistered.getPassword())) {

                    LoggenInRegisteredDto loggenInRegisteredDto = modelMapper.map(rsRegistered, LoggenInRegisteredDto.class);
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user", loggenInRegisteredDto);

                    response.setData(data);
                    httpStatus = HttpStatus.OK;
                } else {
                    httpStatus = HttpStatus.UNAUTHORIZED;
                    response.setError("Not valid credentials");
                }
            } else {
                httpStatus = HttpStatus.UNAUTHORIZED;
                response.setError("Not valid credentials");
            }

        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.prepareErrors(bindingResult);
            response.setError("Form data not valid!");
        }

        response.setStatus(httpStatus);

        return new ResponseEntity<>(response, httpStatus);
    }


}
