package com.example.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

    // method to show form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // method to process the form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // method to read form data and add data to the model
    @RequestMapping("/processFormShout")
    public String shoutData(HttpServletRequest request, Model model) {

        // read request params
        String name = request.getParameter("studentName");

        //convert to upper case
        name = name.toUpperCase();

        // add some data to model
        String data = "Yo! " + name;

        model.addAttribute("data", data);

        return "helloworld";
    }

    @RequestMapping("/processFormShoutv2")
    public String shoutDatav2(@RequestParam("studentName") String name, Model model) {

        /*
        * @RequestParam - to directly link request params to variables
        * Give the form's name value as arg.
        */

        // read request params
        // String name = request.getParameter("studentName");

        //convert to upper case
        name = name.toUpperCase();

        // add some data to model
        String data = "Hey! " + name;

        model.addAttribute("data", data);

        return "helloworld";
    }
}
