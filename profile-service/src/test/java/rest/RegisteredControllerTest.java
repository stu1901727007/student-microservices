package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uni.plovdiv.dto.requests.SignupDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import uni.plovdiv.dto.responces.RegisteredDto;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RegisteredControllerTest {

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


//            SignupDto signupDto = new SignupDto();
//            signupDto.setFirstName("Vitali").setLastName("nesim").setEmail("yest@qweqwe.bg").setPassword("123123123");
            //HttpEntity<SignupDto> requestEntity = new HttpEntity<>(signupDto, headers);
            //order.setProductIds(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
            //URI uri = template.postForLocation("http://localhost:8383/profile/v1/registred/signup",requestEntity);


            ResponseEntity<String> response = template.getForEntity("http://localhost:8383/profile/v1/registered/" + i, String.class);


            if (response.getStatusCode().equals(HttpStatus.OK)) {
                ObjectMapper mapper = new JsonMapper();

                JsonNode root = mapper.readTree(response.getBody());
                JsonNode name = root.path("data").path("user");
                RegisteredDto registeredDto = mapper.convertValue(name, RegisteredDto.class);
                System.out.println("---------------------");
                //System.out.println(name.get("firstName"));
                System.out.println(registeredDto.toString() );
                System.out.println("---------------------");
            } else {
                System.out.println(i + " is empty");
            }


            //Object[] objects = responseEntity.getBody();
            //System.out.println(uri);
            //template.postForObject("http://localhost:8181/profile/v1/registred/signup", signupDto, SignupDto.class);
//            if (order.getStatus() != OrderStatus.REJECTED) {
//                template.put("http://localhost:8090/{id}", null, order.getId());
//            }
        } catch (Exception e) {
            System.out.println(i + " is empty");
        }
    }

}