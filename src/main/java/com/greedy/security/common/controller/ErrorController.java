package com.greedy.security.common.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/error")
public class ErrorController {

	private final MessageSourceAccessor messageSourceAccessor;
	
	public ErrorController(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	@PostMapping("/login")
	public String loginFailed(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message",messageSourceAccessor.getMessage("error.login")); //리터럴리 하게 작성하기 보다 프로퍼티를 등록하고 읽어오도록 한다. 
		return "redirect:/member/login";
	}
	
	@GetMapping("/denied")
	public String accessDenined(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message",messageSourceAccessor.getMessage("error.denied"));
		return "redirect:/";
	}
}

