package com.example.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/silly")

/*
 * @RequestMapping top of the class says all the paths inside the class with have paths relative
 * to this path. Used to resolve ambiguities while resolving paths.
*/

public class SillyController {

    // method to show form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
}
