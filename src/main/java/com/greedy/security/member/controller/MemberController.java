package com.greedy.security.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.security.member.model.dto.CustomUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/login")
	public void loginForm() {}
	
	@GetMapping("/mypage")
	public void mypage(@AuthenticationPrincipal CustomUser user) {
		log.info("로그인 유저 정보 : {}", user);
	}
	
}
