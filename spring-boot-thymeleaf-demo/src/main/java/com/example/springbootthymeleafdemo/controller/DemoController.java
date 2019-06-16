package com.example.springbootthymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/hello")
	public String displayTime(Model model) {
		model.addAttribute("date", new java.util.Date());

		// since using Thymeleaf, Spring Boot will auto-configure to look for
		// helloworld.html inside src/main/resources/templates folder
		return "helloworld";
	}
}
