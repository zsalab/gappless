package com.gappless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BackendApp {

	public static void main(String[] args) {
		SpringApplication.run(BackendApp.class, args);
	}
}
