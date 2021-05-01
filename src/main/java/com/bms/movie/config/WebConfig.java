package com.bms.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final TenantInterceptor tenantInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tenantInterceptor).excludePathPatterns("/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui/**");
	}
}