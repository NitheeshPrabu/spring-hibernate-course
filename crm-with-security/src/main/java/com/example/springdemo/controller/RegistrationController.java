package com.example.springdemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springdemo.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsManager userDetailsManager;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger logger = Logger.getLogger(getClass().getName());

	private Map<String, String> roles;
	@PostConstruct
	protected void loadRoles() {

		// using hashmap, could also read this info from a database
		roles = new LinkedHashMap<String, String>();

		// key = the role, value = display to user
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_ADMIN", "Admin");
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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

		logger.info("Processing registration form for: " + userName);

		// form validation
		if (bindingResult.hasErrors()) {

			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name/password can not be empty.");

			logger.warning("User name/password can not be empty.");

			return "registration-form";
		}

		// check the database if user already exists
		boolean userExists = doesUserExist(userName);

		if (userExists) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exists.");
			model.addAttribute("roles", roles);

			logger.warning("User name already exists.");

			return "registration-form";
		}

		// encrypt the password
		String encodedPassword = passwordEncoder.encode(crmUser.getPassword());

		// prepend the encoding algorithm id
		encodedPassword = "{bcrypt}" + encodedPassword;

		// give user default role of "employee"
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

		// if the user selected role other than employee
		// then add that one too (multiple roles)
		String formRole = crmUser.getFormRole();
		if (!formRole.equals("ROLE_EMPLOYEE")) {
			authorities.add(new SimpleGrantedAuthority(formRole));
		}

		// create user object (from Spring Security framework)
		User tempUser = new User(userName, encodedPassword, authorities);

		// save user in the database
		userDetailsManager.createUser(tempUser);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}

	private boolean doesUserExist(String userName) {

		logger.info("Checking if user exists: " + userName);

		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);

		logger.info("User: " + userName + ", exists: " + exists);

		return exists;
	}

}
