package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(allowPackage("com.example.demo.*.controller")).paths(PathSelectors.any())
				.build();
	}

	private static Predicate<RequestHandler> allowPackage(final String basePackage) {
		return new Predicate<RequestHandler>() {
			@Override
			public boolean apply(RequestHandler input) {
				int index = basePackage.indexOf("*");
				if (index > 1) {
					String prefix = basePackage.substring(0, index - 1);
					String suffix = basePackage.substring(index + 1);
					return declaringClass(input).getPackage().getName().startsWith(prefix)
							&& declaringClass(input).getPackage().getName().endsWith(suffix);
				}
				return declaringClass(input).getPackage().getName().startsWith(basePackage);
			}
		};
	}

	private static Class<?> declaringClass(RequestHandler input) {
		return input.declaringClass();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("springboot利用swagger构建api文档")
				.description("简单优雅的restfun风格，https://www.xingheyun.com").termsOfServiceUrl("https://www.xingheyun.com")
				.version("1.0").build();
	}
}
