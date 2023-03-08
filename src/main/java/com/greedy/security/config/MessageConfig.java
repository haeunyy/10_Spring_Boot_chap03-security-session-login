package com.greedy.security.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	
	/*MessageSource
	 * : code, arguments를 통해 .properties에 저장된 메세지를 읽어오는 메세지를 만들어주는 역할을 하는 클래스 
	 * 스프링에서는 다양한 구현체 클래스를 사용하는데 가장 자주 사용하는 것은 ReloadableResourceBundleMessageSource 클래스*/
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/message");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
	/* MessageSourceAccessor 
	 * : 사용자가 MessageSource 기능을 편리하게 사용할 수 있도록 구현 된 클래스 
	 * 
	 * DefaultLocle을 멤버 변수로 가지고 해당 Locale값을 읽어온다. 
	 * 해당하는 코드의 메세지가 없을 경우 ""값을 전달하여 NullPointerException을 방지한다. 
	 * */
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}
	
	
	
}
