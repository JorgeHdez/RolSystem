package com.kapeks.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kapeks.web.model.Ability;
import com.kapeks.web.service.AbilitiesService;

@Controller
public class MainController {
	AbilitiesService abilitiesService;

	@Autowired
	public MainController(AbilitiesService abilitiesService) {
		this.abilitiesService = abilitiesService;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index() {
		return "redirect:buscar";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu() {
		return "menu";
	}

	@RequestMapping(value = { "/buscar" }, method = RequestMethod.GET)
	public String buscar() {
		return "searchability";
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.GET)
	public String crear() {
		return "createability";
	}

	@RequestMapping(value = { "/abilities" }, method = RequestMethod.POST)
	@ResponseBody
	public List<Ability> abilities() {
		return abilitiesService.getAbilities(null, 10);
	}
}