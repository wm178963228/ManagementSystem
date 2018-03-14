package com.yatoooon.managementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author yatoooon
 */
@Configuration
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".html");
		viewResolver.setRedirectHttp10Compatible(false);
		viewResolver.setContentType("text/html;charset=UTF-8");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
	}
}