package com.kapeks.web.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapeks.web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userService;

	@Autowired
	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = { "/nuevo-usuario" }, method = RequestMethod.GET)
	public String newuser() {
		return "newuser";
	}

	@RequestMapping(value = { "/nuevo-usuario" }, method = RequestMethod.POST)
	public String newuserAdd(Model model, @RequestParam String username, String password, String confirm, boolean admin)
			throws NoSuchAlgorithmException {
		if (username == "" || password == "" || confirm == "") {
			model.addAttribute("error", "No se pudo crear el usuario");
		} else if (!password.equals(confirm)) {
			model.addAttribute("error", "Las contraseñas no son iguales");
		} else {
			userService.addUser(username, password, admin);
			model.addAttribute("msg", "Usuario registrado");
		}
		return "newuser";
	}
}