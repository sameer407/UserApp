package com.zkteco.user.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
@Bean
public Docket api()
{
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.zkteco.user"))
			.paths(regex("/api/v1/users.*"))
			.build()
			.apiInfo(metaInfo());
}


private ApiInfo metaInfo() {
	@SuppressWarnings("deprecation")
	ApiInfo apiInfo= new ApiInfo("User", "Spring-Boot-Application", "1.0", "8553235858", "", "", "");
	return apiInfo;
}
}
