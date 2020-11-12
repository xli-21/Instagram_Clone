package com.csis.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.csis")
//Add to read the database config
@PropertySource("classpath:database.properties")
public class SpringMVCConfig extends WebMvcConfigurationSupport{    
	
	//Need the environment available
	@Autowired
	Environment environment;
	//some properties for db connections
	private final String URL = "url";
	private final String USER = "dbuser";
	private final String DRIVER = "driver";
	private final String PASSWORD = "dbpassword";

	
	//Wire up the datasource bean
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	
	//View, this class resolves the view name to the .jsp file.    
	@Bean    
	public ViewResolver viewResolver() {        
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();        
		viewResolver.setViewClass(JstlView.class);        
		viewResolver.setPrefix("/WEB-INF/views/");        
		viewResolver.setSuffix(".jsp");        
		return viewResolver;    
		}    
	
	//These are the messages we can pass back and forth between the controller model and view.    
	@Bean    
	public MessageSource messageSource() {        
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();       
		messageSource.setBasename("messages");        
		return messageSource;    
	}    
	
	//This is simply to allow any file requests past static to go through to the"/static" folder, we can add things like images css etc..    
	@Override    
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");    }
}

