package com.evgateway.cpohubserver.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("")
	public String welcome() {
		return "Welcome CPO-HUB-BackEnd Server.";
	}
    
}
