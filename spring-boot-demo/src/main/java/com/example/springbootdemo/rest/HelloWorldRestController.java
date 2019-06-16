package com.example.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorldRestController {

	@Value("${coach.name}")
	private String coach;

	@Value("${team.name}")
	private String team;

	@GetMapping("/")
	public String printTime() {
		return "Hello World! Current time is " + LocalDateTime.now();
	}

	@GetMapping("/workout")
	public String getWorkout() {
		return "Run a hard 5k";
	}

	@GetMapping("/fortune")
	public String getFortune() {
		return "Today is your lucky day!";
	}

	@GetMapping("/teaminfo")
	public String getTeamInfo() {
		return "Coach: " + coach + ", Team: " + team;
	}

}
