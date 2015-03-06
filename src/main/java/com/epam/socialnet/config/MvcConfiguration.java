package com.epam.socialnet.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epam.socialnet.dao.PersonDAO;
import com.epam.socialnet.dao.PersonDAOImpl;
import com.epam.socialnet.services.PersonService;
import com.epam.socialnet.services.PersonServiceImpl;

@Configuration
@ComponentScan(basePackages="com.epam.socialnet")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/socialnet?charSet=UNICODE");
		dataSource.setUsername("postgres");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	
	@Bean
	public PersonDAO getPersonDAO() {
		return new PersonDAOImpl(getDataSource());
	}
	
	@Bean
	public PersonService getPersonService() {
		return new PersonServiceImpl(getPersonDAO()); 
	}
	
	@Bean
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setMaxUploadSize(5 * 1024 * 1024);
//	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
}
