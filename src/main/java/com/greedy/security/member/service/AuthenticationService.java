package com.greedy.security.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.security.member.model.dao.MemberMapper;
import com.greedy.security.member.model.dto.CustomUser;
import com.greedy.security.member.model.dto.MemberDTO;
import com.greedy.security.member.model.dto.MemberRoleDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j 	// Lombok에서 제공하는 어노테이션으로 log라는 이름으로 필드 선언을 제공한다. 
@Service
public class AuthenticationService implements UserDetailsService {

//	private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
//	-> @Slf4j 으로 간단하게 사용가능하다. 
	
	private final MemberMapper memberMapper;
	
	public AuthenticationService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/* System.out을 사용하기 보다는 Log 출력을 사용하는 것이 성능상 좋다.
		 * Log4j <-> Logback ( Slf4j - 추상체 ) 
		 * Logback이 스프링부트의 기본 설정이다. 
		 * 
		 * log level
		 * 1. error : 요청을 처리하는 중 오류가 발생 
		 * 2. warn : 처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메세지 
		 * 3. info : 상태 변경과 같은 정보성 로그
		 * 4. debug : 프로그램을 디버깅 하기 위한 용도 
		 * 5. trace : 디버그보다 훨씬 상세한 정보 
		 * 
		 * 기본 로그 레벨은 info 레벨로 설정되어 있어 info 이하의 레벨은 출력되지 않는다.
		 * application.yml 파일에서 log level을 변경할 수 있다. */
		System.out.println("username: "+ username);
		
		/* 중괄호는 자리 맡음 */
		log.error("username : {}", username);
		log.warn("username : {}", username);
		log.info("username : {}", username);
		log.debug("username : {}", username);
		log.trace("username : {}", username);
		
		/* 사용자 정의 타입으로 유저 조회 */
		MemberDTO member = memberMapper.findMemberById(username);
		
		log.info("member : {}", member);
		
		/* 조회 된 값이 없을 시 처리 (아이디가 데이터베이스에 존재하지 않음) */
		if(member == null) throw new UsernameNotFoundException("username not found");
		
		/* 권한 리스트 만들기 */
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MemberRoleDTO role : member.getMemberRoleList()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
		}
		
//		return new User(member.getId(), member.getPwd(), authorities);
		/* User 객체에 담기지 않는 추가 정보를 User를 extends한 CustomUser에 담아서 리턴한다. */
		return new CustomUser(member, authorities);
		
	}

}
