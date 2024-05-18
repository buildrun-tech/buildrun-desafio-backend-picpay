package tech.buildrun.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayApplication.class, args);
	}

}
