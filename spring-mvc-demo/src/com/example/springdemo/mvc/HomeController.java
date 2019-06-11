package com.example.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* @Controller - used to specify class is a controller. Extends @Component so
* that scanning is supported
*
* @RequestMapping - specifies the relative path with which you can render the following method
* Returns a view name that is prefixed and suffixed as per xml config
* Method can have any unique name
*/

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showPage() {
		return "main-menu";
	}
}
