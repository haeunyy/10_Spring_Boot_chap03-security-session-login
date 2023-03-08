package com.greedy.security.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.greedy.security", annotationClass = Mapper.class)
public class MybatisConfig {

	
	
}
