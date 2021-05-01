package com.bms.movie.config;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.bms.movie.exceptions.BookMyShowBadRequestException;
import com.bms.movie.tenant.Tenant;
import com.bms.movie.tenant.TenantRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantInterceptor implements WebRequestInterceptor {

	public static final String TENANT_HTTP_HEADER = "X-Tenant";

	private final TenantRepository tenantRepository;

	@Override
	public void preHandle(WebRequest request) throws Exception {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		String tenantId = request.getHeader(TENANT_HTTP_HEADER);
		if (!ObjectUtils.isEmpty(tenantId)) {
			Tenant tenant = tenantRepository.findById(tenantId)
					.orElseThrow(() -> new BookMyShowBadRequestException("Tenant header not valid."));
			String requestUri = servletWebRequest.getRequest().getRequestURI();

			if (requestUri.startsWith("/admin") && !tenant.getName().equalsIgnoreCase("tenant"))
				throw new BookMyShowBadRequestException("Access Denied");

			if (tenant.getName().equalsIgnoreCase("tenant") && !requestUri.startsWith("/admin"))
				throw new BookMyShowBadRequestException("Access Denied");

			TenantContext.setTenantId(tenant.getName());
			log.info("Tenant header get: {}", tenantId);
		} else {
			log.error("Tenant header not found.");
			throw new BookMyShowBadRequestException("Tenant header not found.");

		}

	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		TenantContext.clear();

	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
