package com.bms.movie.tenant;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bms.movie.config.MapperCustomConfig;

@Mapper(config = MapperCustomConfig.class)
public interface TenantMapper extends Converter<Tenant, TenantDto> {

	@Override
	TenantDto convert(Tenant tenant);

}
