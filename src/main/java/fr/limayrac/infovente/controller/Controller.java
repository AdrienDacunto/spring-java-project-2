package fr.limayrac.infovente.controller;

import fr.limayrac.infovente.model.User;
import fr.limayrac.infovente.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UserRepository userRepo;
	
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
		logger.info(user.toString());
		userRepo.save(user);
		
		return "index";
	}
	
	@GetMapping("/categorie_ordinateurs")
	public String viewCategoriePageOrdinateurs(Model model) {
		return "categorie_ordinateurs";
	}

	@GetMapping("/categorie_smartphones")
	public String viewCategoriePageSmartphones(Model model) {
		return "categorie_smartphones";
	}

	@GetMapping("/panier")
	public String viewPanierPage(Model model) {
		return "panier";
	}
}








