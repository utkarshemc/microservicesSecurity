/**
 * 
 */

package com.dell.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author bhardu
 * @Since May 8, 2020
 */
@Configuration

@EnableGlobalMethodSecurity(prePostEnabled = true)

@Order(1000)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/user/", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui.html", "/webjars/**", "/user/signup", "/user/login")
				.permitAll().anyRequest().authenticated();
	}

}
