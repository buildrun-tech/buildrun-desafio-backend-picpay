package tech.buildrun.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PicPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicPayApplication.class, args);
	}

}
