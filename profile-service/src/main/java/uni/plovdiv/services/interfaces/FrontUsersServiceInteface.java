package uni.plovdiv.services.interfaces;

import uni.plovdiv.models.FrontUser;
import uni.plovdiv.requests.FrontUserDto;
import uni.plovdiv.requests.FrontUserSignupDto;


public interface FrontUsersServiceInteface {

    FrontUser getById(long frontUserId);

    FrontUser getByEmail(String email);
    
    FrontUser update(FrontUserDto frontUserDto, FrontUser frontUser);

    FrontUser create(FrontUserDto frontUserDto);

    FrontUser signup(FrontUserSignupDto frontUserSignupDto);

    boolean delete(FrontUser frontUser);
}
