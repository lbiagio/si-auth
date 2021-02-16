package com.biagio.siauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class SiAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiAuthApplication.class, args);
	}

}
