package com.bms.movie.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("cookie", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .description("RegionCookie")
                                .in(SecurityScheme.In.COOKIE)
                                .name("region")
                        ))
						.security(Arrays.asList(
		                        new SecurityRequirement().addList("cookie"))
						)
				.info(new Info().title("Movies API").description("Spring shop sample application").version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}
}
