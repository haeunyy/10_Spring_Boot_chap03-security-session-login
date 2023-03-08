package com.greedy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.security.member.service.AuthenticationService;

@EnableWebSecurity	// 시큐리티 설정 활성화 및 bean 등록
public class SpringSecurityConfig {
	
	private final AuthenticationService authenticationService;
	
	public SpringSecurityConfig(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/* 1. 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	// 랜덤 솔팅 
	}
	
	/* 2. HTTP 요청에 대한 설정, 반환값 설명 다시 듣기 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				/* csrf는 기본적으로 활성화 되어있으므로 비활성화 처리 (연습용이라서)*/
				.csrf() // 웹취약점에 대한 공격막아주는 기능 사용하지 않겠다 .
					.disable()
					/* 요청에 대한 권한 체크 */
					.authorizeHttpRequests()
						/* hasRole 안에 전달하는 값은 "ROLE_" 가 자동으로 붙는다.-> ROLE_MEMBER 인경우 뒤의 MEMBER만 붙인다.*/
						.antMatchers("/order/**", "/member/mypage").hasRole("MEMBER") // 앞의 권한들은 MEMBER라는 role을 가져야 할수있다.  
						.antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
						.antMatchers("/admin/**").hasRole("ADMIN")
						/* 위에 서술된 내용 외의 모든 요청은 허가함 (인증되지 않은 사용자도 요청 가능) */
						.anyRequest().permitAll()
					.and()
						/* 로그인 설정 */
						.formLogin()
						/* 로그인 페이지 설정 */
						.loginPage("/member/login")
						/* 성공 시 랜딩 페이지 설정 */
						.successForwardUrl("/")
						/* 실패 시 랜딩 페이지 설정 */
						.failureForwardUrl("/error/login")
					.and()
					 	/* 로그아웃 설정 */
						.logout()
						/* 로그아웃 주소 */
						.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
						/* 세션 만료 */
						.invalidateHttpSession(true)
						/* JSESSIONID 쿠키 삭제 */
						.deleteCookies("JSESSOIONID")
						/* 로그아웃 후 랜딩 페이지 */
						.logoutSuccessUrl("/")
					.and()
						/* 예외 처리 */
						.exceptionHandling()
						/* 인증이 필요한 때에는 로그인 페이지로 이동하게 된다. 
						 * 인가가 되지 않았을때의 랜딩 페이지를 설정한다.*/
						.accessDeniedPage("/error/denied")
					.and()
						.build();
	}
	/* 3. 사용자 인증을 위해서 사용할 Service bean 등록 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http
					.getSharedObject(AuthenticationManagerBuilder.class)
					.userDetailsService(authenticationService)
					.passwordEncoder(passwordEncoder())
					.and()
					.build();
	}
}
