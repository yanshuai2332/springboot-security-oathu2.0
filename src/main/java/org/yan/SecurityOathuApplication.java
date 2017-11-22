package org.yan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "org.yan.user.repository")
public class SecurityOathuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOathuApplication.class, args);
	}
}
