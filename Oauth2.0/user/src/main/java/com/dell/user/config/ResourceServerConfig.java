/**
 * 
 */

package com.dell.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author bhardu
 * @Since May 8, 2020
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/user/", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui.html", "/webjars/**", "/user/signup", "/user/login")
				.permitAll().anyRequest().authenticated();
	}

}
