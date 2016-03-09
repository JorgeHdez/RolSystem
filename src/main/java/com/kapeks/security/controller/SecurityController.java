package com.kapeks.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		if (error != null)
			model.addAttribute("error", "Usuario o contraseña incorrectos");

		if (logout != null)
			model.addAttribute("msg", "Te has desconectado");

		return "login";
	}

	@RequestMapping(value = { "/denied" }, method = RequestMethod.GET)
	public String denied() {
		return "denied";
	}
}