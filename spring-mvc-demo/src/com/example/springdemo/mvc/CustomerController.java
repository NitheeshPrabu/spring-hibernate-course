package com.example.springdemo.mvc;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    /*
    * @InitBinder - used to define a preprocessing method that handles the model data
    */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Used to remove all leading and trailing whitespaces.
        // By passing true as arg, if only whitespaces present, returns null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @RequestMapping("/processForm")
    /*
    * In the method signature, the @BindingResult parameter must be immediately after the model attribute.
    * Otherwise the validation rules will be ignored
    * @Valid indicates that there are some validation rules involved for this model object.
    */
    public String processForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
