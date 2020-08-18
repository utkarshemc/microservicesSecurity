/**
 * 
 */
package com.dell.user.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bhardu
 * @Since May 3, 2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("Authorization") // name of header
				.modelRef(new ModelRef("string")).parameterType("header") // type - header
				.defaultValue("Bearer em9uZTpteXBhc3N3b3Jk") // based64 of - zone:mypassword
				.required(false) // for compulsory
				.build();
		java.util.List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(aParameterBuilder.build()); // add parameter
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(metaData()).globalOperationParameters(aParameters);
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("User Service",
				"User Service REST API for Payment System to display security protocols", "1.0", "Terms of service",
				new Contact("Utkarsh Bhardwaj", "https://springframework.guru/about/", "utkarsh.bhardwaj@dell.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
