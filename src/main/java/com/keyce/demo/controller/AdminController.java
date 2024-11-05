package com.keyce.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
	@GetMapping
	public String getHome() {
		return "<html><body><h1>Ceci est une page protégé uniquement pour les admins</h1>"
				+ "<div><a href=\"/user/populate\">Populer</div>"
				+ "<div><a href=\"/user\">Liste des utilisateurs</div>"
				+ "<body></html>";
	}
}
