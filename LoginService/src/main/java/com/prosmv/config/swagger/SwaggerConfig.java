package com.prosmv.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is configuration class for Swagger.
 * 
 * @author piyush
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		// adding a custom parameter in swagger
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false)
				.build();

		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(parameterBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().globalOperationParameters((aParameters));
	}

}