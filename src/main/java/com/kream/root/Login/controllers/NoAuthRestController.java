package com.kream.root.Login.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class NoAuthRestController {
	
	@GetMapping(value = "/favicon.ico")
	public void favicon(HttpServletRequest request) {
	}
		

}
