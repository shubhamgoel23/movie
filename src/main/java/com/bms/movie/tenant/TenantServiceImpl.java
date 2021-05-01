package com.bms.movie.tenant;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.movie.exceptions.BookMyShowBadRequestException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
	
	private final TenantRepository tenantRepository;
	private final ConversionService conversionService;

	
	@Override
	@Transactional
	public TenantDto createTenant(String tenant) {
		Tenant tenant2 = tenantRepository.save(Tenant.builder().name(tenant).build());
//		if(1+1==2)throw new BookMyShowBadRequestException("testing");
		return conversionService.convert(tenant2, TenantDto.class);
	}

}
