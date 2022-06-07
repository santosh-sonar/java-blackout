package com.contrast.commandinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/processBuilder")
	public String processBuilder(@RequestParam String command) throws IOException {
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();

			String result = new String(process.getInputStream().readAllBytes());
			return String.format("%s", result);
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@GetMapping("/exec")
	public String exec(@RequestParam String command) throws IOException {
		try {
			Process process = Runtime.getRuntime().exec(command);
			String result = new String(process.getInputStream().readAllBytes());
			return String.format("%s", result);
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	// @GetMapping("/el")
	// public String el(@RequestParam String message) {
	// 	return "redirect:/error?msg=error.generic";
	// }

	// @PostMapping("/create")
	// public String create(@RequestParam String filename) throws IOException {
	// try {
	// Process process = Runtime.getRuntime().exec("touch " + filename);
	// return String.format("PID: %s", process.pid());
	// } catch (Exception ex) {
	// throw new FileNotFoundException();
	// }
	// }

	// @PutMapping("/update")
	// public String update(@RequestParam String filename) throws IOException {
	// try {
	// Process process = Runtime.getRuntime().exec("touch " + filename);
	// return String.format("PID: %s", process.pid());
	// } catch (Exception ex) {
	// throw new FileNotFoundException();
	// }
	// }

	// @DeleteMapping
	// public String delete(@RequestParam String filename) throws IOException {
	// try {
	// Process process = Runtime.getRuntime().exec("touch " + filename);
	// return String.format("PID: %s", process.pid());
	// } catch (Exception ex) {
	// throw new FileNotFoundException();
	// }
	// }

}