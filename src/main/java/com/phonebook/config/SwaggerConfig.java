package com.phonebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
     @Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				         .groupName("public-api")
				         .apiInfo(apiInfo())
				         .select()
				         .apis(RequestHandlerSelectors.basePackage("com.phonebook.controller"))
				         .paths(PathSelectors.any())
				         .build();
	}
     private ApiInfo apiInfo() {
    	 return new ApiInfoBuilder().title("PhoneBook API")
    			 .description("PhoneBook API refernce for developer")
    			 .termsOfServiceUrl("http://www.ashokit.in")
    			 .contact("ashokit@gmail.com").license("Ashok IT License")
    			 .licenseUrl("ashpkit@gmail.com").version("1.0").build();
    	 
     }
}
