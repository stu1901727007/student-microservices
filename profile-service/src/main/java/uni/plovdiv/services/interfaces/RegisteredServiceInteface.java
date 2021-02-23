package uni.plovdiv.services.interfaces;

import uni.plovdiv.dto.requests.RegisteredDto;
import uni.plovdiv.models.Registered;


public interface RegisteredServiceInteface {

    Registered update(RegisteredDto registeredDto, Registered registered);

    Registered create(RegisteredDto registeredDto);

    Boolean delete(Registered registered);
}
