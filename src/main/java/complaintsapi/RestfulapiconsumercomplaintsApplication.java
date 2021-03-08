package complaintsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages="complaintsapi.model")
@ComponentScan(basePackages= {"complaintsapi.*"})
@EnableJpaRepositories(basePackages= {"complaintsapi.repository"})
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
public class RestfulapiconsumercomplaintsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulapiconsumercomplaintsApplication.class, args);
	}	

}
