package rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uni.plovdiv.dto.FrontUserDto;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontUserControllerTest {

    RestTemplate template = new RestTemplate();

    @Test
    public void loginUser() throws IOException, HttpClientErrorException {
        for (int i = 0; i < 10; i++) {
            loginRegistred(i);
        }

        assertEquals(true, true);
    }

    void loginRegistred(int i) throws IOException, HttpClientErrorException {

        try {
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("firstName", "vitali"));
//            params.add(new BasicNameValuePair("lastName", "atias"));
//            params.add(new BasicNameValuePair("email", "vitali@fit.bg"));
//            params.add(new BasicNameValuePair("password", "123456"));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


//            FrontUserSignupDto signupDto = new FrontUserSignupDto();
//            signupDto.setFirstName("Vitali").setLastName("nesim").setEmail("yest@qweqwe.bg").setPassword("123123123");
            //HttpEntity<FrontUserSignupDto> requestEntity = new HttpEntity<>(signupDto, headers);
            //order.setProductIds(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
            //URI uri = template.postForLocation("http://localhost:8383/profile/v1/registred/signup",requestEntity);


            ResponseEntity<String> response = template.getForEntity("http://localhost:8383/profile/v1/registered/" + i, String.class);


            if (response.getStatusCode().equals(HttpStatus.OK)) {
                ObjectMapper mapper = new JsonMapper();

                JsonNode root = mapper.readTree(response.getBody());
                JsonNode name = root.path("data").path("user");
                FrontUserDto frontUserDto = mapper.convertValue(name, FrontUserDto.class);
                System.out.println("---------------------");
                //System.out.println(name.get("firstName"));
                System.out.println(frontUserDto.toString() );
                System.out.println("---------------------");
            } else {
                System.out.println(i + " is empty");
            }

            //Object[] objects = responseEntity.getBody();
            //System.out.println(uri);
            //template.postForObject("http://localhost:8181/profile/v1/registred/signup", signupDto, FrontUserSignupDto.class);
        } catch (Exception e) {
            System.out.println(i + " is empty");
        }
    }

}