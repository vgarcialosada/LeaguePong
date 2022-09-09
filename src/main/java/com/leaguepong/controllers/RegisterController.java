package com.leaguepong.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.leaguepong.entities.Usuario;

import ch.qos.logback.classic.Logger;

@Controller
public class RegisterController {
	@GetMapping("/register")
	public String greetingForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "register";
	}

	@PostMapping("/register")
	public String greetingSubmit(@ModelAttribute Usuario usuario, Model model) {
	   Logger logger = (Logger) LoggerFactory.getLogger(RegisterController.class);
	   logger.info(usuario.toString());

		model.addAttribute("usuario", usuario);
		return "userResult";
	}

}
