package com.bms.movie.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bms.movie.context.TenantContext;
import com.bms.movie.exceptions.BookMyShowBadRequestException;
import com.bms.movie.tenant.Tenant;
import com.bms.movie.tenant.TenantRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantInterceptor implements HandlerInterceptor {

	public static final String TENANT_HTTP_HEADER = "X-Tenant";

	private final TenantRepository tenantRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		String tenantId = request.getHeader(TENANT_HTTP_HEADER);
		if (ObjectUtils.isEmpty(tenantId)) 
			throw new BookMyShowBadRequestException("Tenant header not found.");

		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new BookMyShowBadRequestException("Tenant header not valid."));
		
//		String requestUri = request.getRequestURI();
		if (requestUri.startsWith("/admin") && !tenant.getName().equalsIgnoreCase("tenant"))
			throw new BookMyShowBadRequestException("Access Denied");

		if (tenant.getName().equalsIgnoreCase("tenant") && !requestUri.startsWith("/admin"))
			throw new BookMyShowBadRequestException("Access Denied");

		TenantContext.setTenant(tenant.getName());
		log.info("Tenant context: {}", TenantContext.getTenant());

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.info("Cleared Tenant Context");
		TenantContext.clear();
	}

}
