package com.booktracker.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booktracker.properties.User;

@Controller
public class LoginController {
	
	@Autowired
	private User user;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String displayMenu(ModelMap model, Principal principal) {

		String name = principal.getName();
		user.setUser(name);
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Form");
		return "menu";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

	}

}