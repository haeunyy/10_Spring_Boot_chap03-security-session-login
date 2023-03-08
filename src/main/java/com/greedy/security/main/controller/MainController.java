package com.greedy.security.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/main"})
	public String main() {
		return "main/main";
	}
	
	@PostMapping(value="/")
	public String redirectMain() {
		/* 성공했을 때 원하는 기능 작성 가능 */
		
		return "redirect:/";
	}
	
}
