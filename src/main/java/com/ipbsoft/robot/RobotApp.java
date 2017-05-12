package com.ipbsoft.robot;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ipbsoft.robot.config.AppConfig;

/**
 * RobotApp main class that registers a Spring Dispatcher servlet with annotation-based class configuration
 * 
 * @author insalada
 *
 */
public class RobotApp extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}	
}
