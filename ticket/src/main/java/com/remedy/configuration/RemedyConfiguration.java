package com.remedy.configuration;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.remedy.controller.UserController;
import com.remedy.exception.DatabaseAuthenticationException;

/*Author Abdul Wahid*/

@Configuration
@ComponentScan(basePackages = { "com.remedy.controller", "com.remedy.service", "com.remedy.exception" })
@EnableJpaRepositories(basePackages = "com.remedy.dao")
@EntityScan(basePackages = "com.remedy.entity")
@EnableWebSecurity
public class RemedyConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		logger.info("In JDBC Authentication");
		auth.jdbcAuthentication().dataSource(dataSource()).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery(
						"select manager_name,encoded_password,enabled from user_registration where manager_name=?")
				.authoritiesByUsernameQuery(
						"select u.manager_name as username, r.roles as role from user_registration u INNER JOIN roles r ON r.role_id = u.role_role_id where manager_name=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("In Http Authorize");

		try {
			http.authorizeRequests()
					.antMatchers("/css/*", "/js/*", "/img/*", "/registration", "/register", "/login", "/").permitAll()
					.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
					.permitAll().and().logout().and().logout().permitAll();
		} catch (Exception e) {
			throw new DatabaseAuthenticationException(
					"Exception Caught in the Spring Security Method : configure(HttpSecurity Method)");
		}
		http.csrf().disable();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // this is
																// depretected
																// com.mysql.cj.jdbc.Driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/tickets");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

}
