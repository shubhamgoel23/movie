package com.bms.movie.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.bms.movie.tenant.Tenant;
import com.bms.movie.tenant.TenantRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadInitData {

	private final TenantRepository tenantRepository;

	@PostConstruct
	public void init() {
		TenantContext.setTenantId("tenant");
		if (tenantRepository.existsByName("tenant"))
			return;

		tenantRepository.save(Tenant.builder().name("tenant").build());
		TenantContext.clear();

	}
}
