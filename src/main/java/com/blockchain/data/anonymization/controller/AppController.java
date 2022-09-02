package com.blockchain.data.anonymization.controller;

import com.blockchain.data.anonymization.Repository.UserRepository;
import com.blockchain.data.anonymization.model.CovidUser;
import com.blockchain.data.anonymization.model.User;
import com.blockchain.data.anonymization.service.CovidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CovidUserService covidUserService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}

	@GetMapping("/covidRegister")
	public String showCovidRegistrationForm(Model model) {
		model.addAttribute("covidUser", new CovidUser());
		return "covid_register_form";
	}
	
/*	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}*/

	@GetMapping("/users/covid")
	public String showCovidCase(Model model) {
		model.addAttribute("count", "");

		return "view_covid";
	}

	@GetMapping("/users/retriveCovidCount")
	public String retrieveCovidCasesInMyArea(Model model, @RequestParam String email){
		Integer covidCasesInArea = covidUserService.getCovidCasesInArea(email);
		model.addAttribute("covidCasesInArea", covidCasesInArea.toString());
		return "covid_count";
	}
}
