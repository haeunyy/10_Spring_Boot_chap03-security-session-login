package com.greedy.security.member.model.dto;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomUser extends User {

	private int no;				// 회원번호 
	private String name;		// 회원이름
	private Date registDate;	// 회원가입 일시
	
	public CustomUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
		super(member.getId(), member.getPwd(), authorities);
		setDetails(member);
	}

	private void setDetails(MemberDTO member) {
		this.no = member.getNo();
		this.name = member.getName();
		this.registDate = member.getRegistDatetime();
	}
	
	
}
