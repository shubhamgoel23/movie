package com.bms.movie.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegionContext {

	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	private RegionContext() {

	}

	public static void setRegion(String region) {
		log.debug("Setting region to {}", region);
		CONTEXT.set(region);

	}

	public static String getRegion() {
		return CONTEXT.get();

	}

	public static void clear() {
		CONTEXT.remove();

	}
}
