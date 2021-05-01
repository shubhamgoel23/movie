package com.bms.movie.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	public static void setTenantId(String tenantId) {
		log.debug("Setting tenantId to " + tenantId);
		CONTEXT.set(tenantId);

	}

	public static String getTenantId() {
		return CONTEXT.get();

	}

	public static void clear() {
		CONTEXT.remove();

	}

}
