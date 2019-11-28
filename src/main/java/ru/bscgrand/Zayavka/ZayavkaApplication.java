package ru.bscgrand.Zayavka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.bscgrand.Zayavka.Configurations.ExcelConfigProperties;

@SpringBootApplication
@EnableScheduling
public class ZayavkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZayavkaApplication.class, args);
	}

}
