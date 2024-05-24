package tech.buildrun.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API de integração Rest-Client"))
public class PicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayApplication.class, args);
	}

}
