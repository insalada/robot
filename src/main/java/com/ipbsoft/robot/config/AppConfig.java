package com.ipbsoft.robot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Annotation-based configuration for MVC Spring context
 * 
 * @author insalada
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ipbsoft.robot")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
