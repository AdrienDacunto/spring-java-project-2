package fr.limayrac.infovente.controller;


import fr.limayrac.infovente.model.Cart;
import fr.limayrac.infovente.model.Product;
import fr.limayrac.infovente.model.User;
import fr.limayrac.infovente.repository.CartRepository;
import fr.limayrac.infovente.repository.ProductRepository;
import fr.limayrac.infovente.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CartRepository cartRepository;

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

	@PostMapping("/articles/addToCart")
	public String addToCart(@RequestParam Long productId, Principal principal) {


		// Récupérer le produit à partir de la base de données
		Product product = productRepo.findProductById(productId);

		// Récupérer ou créer le panier de l'utilisateur (supposons que vous utilisez un identifiant utilisateur)
		User user = userRepo.findByEmail(principal.getName());

		Cart cart = cartRepository.findCartById(user.getId());

		if(cart == null)
		{
			Cart Newcart = new Cart();
			Newcart.addUser(user.getId());

			// Ajouter le produit au panier
			Newcart.addItem(product);

			// Sauvegarder le panier dans la base de données
			cartRepository.save(Newcart);

			return "categorie_ordinateurs";
		}
		// Ajouter le produit au panier
		cart.addItem(product);

		// Sauvegarder le panier dans la base de données
		cartRepository.save(cart);

		return "categorie_ordinateurs";
	}
	@GetMapping("/categorie_ordinateurs")
	public String viewCategoriePageOrdinateurs(Model model) {
		List<Product>articles = productRepo.findByCategory("ordinateurs");
		model.addAttribute("articles", articles);
		return "categorie_ordinateurs";
	}

	@GetMapping("/categorie_smartphones")
	public String viewCategoriePageSmartphones(Model model) {
		List<Product> articles = productRepo.findByCategory("smartphones");
		model.addAttribute("articles", articles);
		return "categorie_smartphones";
	}

	@GetMapping("/registers")
	public String viewPanierPage(Model model, Principal principal) {
		// Récupérer l'utilisateur connecté (supposons que vous utilisez un identifiant utilisateur)
		User user = userRepo.findByEmail(principal.getName());

		// Récupérer les produits dans le panier de l'utilisateur depuis la base de données
		List<Product> products = cartRepository.findCartById(user.getId()).getItems();

		// Ajouter les produits au modèle
		model.addAttribute("articles", products);

		return "flows/register/panier";
	}
}








