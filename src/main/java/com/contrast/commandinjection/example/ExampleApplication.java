package com.contrast.commandinjection.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
@RestController
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@GetMapping("/create-file")
	public String createFile(@RequestParam String filename) throws IOException {
		try {
			Process process = Runtime.getRuntime().exec("touch " + filename);
			return String.format("PID: %s", process.pid());
		} catch (Exception ex) {
			throw new FileNotFoundException();
		}
	}
}