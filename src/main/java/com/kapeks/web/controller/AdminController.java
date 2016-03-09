package com.kapeks.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = { "nuevo-usuario" }, method = RequestMethod.GET)
	public String newuser() {
		return "newuser";
	}
}
