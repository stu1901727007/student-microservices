package uni.plovdiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import uni.plovdiv.services.order.repository.OrderRepository;

@SpringBootApplication
@EnableFeignClients
public class BillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingApplication.class, args);
    }

    @Bean
    OrderRepository repository() {
        return new OrderRepository();
    }
}
