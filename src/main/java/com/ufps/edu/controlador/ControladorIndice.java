package com.ufps.edu.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorIndice {

	@GetMapping({"/","/login",""})
	public String index() {
		return "login";
	}
	
	@PostMapping ("/login")
	public String login() {
			return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/logout")
	public String salir() {
		return "login";
	}
}
