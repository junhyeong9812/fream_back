package com.kream.root.Login.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoAuthController {
	@GetMapping(value = "/")
	public String mainPage(HttpServletRequest request) {
		System.out.println("MainPage");
		
		return "MainPage";
	}
	@GetMapping(value = "/gamePage")
	public String gamePage(HttpServletRequest request) {
		System.out.println("gamePage");
		
		return "gamePage";
	}
	
	
}
