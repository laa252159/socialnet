package net.codejava.spring.config;

import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.dao.PersonDAO;
import net.codejava.spring.dao.PersonDAOImpl;
import net.codejava.spring.model.Person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epam.socialnet.services.PersonService;
import com.epam.socialnet.services.PersonServiceImpl;

@Configuration
@ComponentScan(basePackages="net.codejava.spring")
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
		dataSource.setUrl("jdbc:postgresql://localhost:5432/socialnet");
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
}
