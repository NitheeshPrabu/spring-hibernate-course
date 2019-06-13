package com.example.springsecuritydemo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.example.springsecuritydemo.entity.Role;
import com.example.springsecuritydemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsecuritydemo.service.UserService;
import com.example.springsecuritydemo.user.CrmUser;
import com.example.springsecuritydemo.entity.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private LinkedHashMap<String, String> roles;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@PostConstruct
	protected void loadRoles() {
		roles = new LinkedHashMap<>();
		List<Role> roleList = roleService.getAllRoles();

		for (Role role : roleList) {
			roles.put(role.getName(), role.getName().substring(5,6) + role.getName().substring(6).toLowerCase());
		}
	}

	@GetMapping("/showRegistrationForm")
	public String showMyRegistrationPage(Model model) {

		model.addAttribute("crmUser", new CrmUser());
		model.addAttribute("roles", roles);

		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("crmUser") CrmUser crmUser,
			BindingResult bindingResult,
			Model model) {

		String userName = crmUser.getUserName();
		myLogger.info("Processing registration form for: " + userName);

		// give user default role of "employee"
		List<String> userRoleNames = new ArrayList<>();
		userRoleNames.add("ROLE_EMPLOYEE");

		// if the user selected role other than employee
		// then add that one too (multiple roles)
		String formRole = crmUser.getFormRole();
		if (!formRole.equals("ROLE_EMPLOYEE")) {
			userRoleNames.add(formRole);
		}

		// form validation
		if (bindingResult.hasErrors()){
			return "registration-form";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null){
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exists.");
			model.addAttribute("roles", roles);

			myLogger.warning("User name already exists.");
			return "registration-form";
		}

		// create user account
		userService.save(crmUser, userRoleNames);

		myLogger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}
}
