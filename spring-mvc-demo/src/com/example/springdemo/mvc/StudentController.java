package com.example.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    // method for showing form
    @RequestMapping("/showForm")
    public String showForm(Model model) {

        // create a new student object
        Student student = new Student();

        // add student object as a model attribute
        model.addAttribute("student", student);

        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {

        /*
        * @ModelAttribute - used with Spring Forms. Represents the model of data that is returned
        * from the form. Pass the form's modelAttribute value as arg.
        */

        // log the input data
        System.out.println("student: " + student.getFirstName() + " " + student.getLastName());

        return "student-confirmation";
    }
}
