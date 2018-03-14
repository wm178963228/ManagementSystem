package com.yatoooon.managementsystem.config;

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
public class Swagger2Config {

	//    http://localhost:8080/swagger-ui.html
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yatoooon.managementsystem.controller"))
				.paths(PathSelectors.any())
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("项目接口文档说明")
				.description("项目地址")
				.termsOfServiceUrl("https://github.com/yatoooon/managementsystem")
				.contact("yatoooon")
				.version("1.0")
				.build();
	}
}
