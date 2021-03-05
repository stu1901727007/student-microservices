package uni.plovdiv.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptUtils {

    private static final BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder(12);

    /**
     *
     * @param plainText
     * @return
     */
    public String bcryptEncryptor(String plainText) {
        return passwordEcorder.encode(plainText);
    }

    /**
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEcorder.matches(rawPassword, encodedPassword);
    }

}
