package com.kapeks.web.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kapeks.security.model.AppUser;
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
	public String newuserAdd(Model model, @RequestBody AppUser user) throws NoSuchAlgorithmException {
		if (user != null) {
			userService.addUser(user);
			model.addAttribute("msg", "Usuario registrado");
		} else
			model.addAttribute("error", "No se pudo crear el usuario");
		return "newuser";
	}
}