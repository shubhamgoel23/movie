package com.bms.movie.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RegionSupplier {

	private RegionSupplier() {
	};

	public static BiFunction<HttpServletRequest, String, Optional<String>> getCookieByKey = (request, key) -> {
		return Arrays.stream(request.getCookies()).filter(c -> key.equals(c.getName())).map(Cookie::getValue).findAny();
	};

	public static Function<Optional<String>, String> validateRegion = optReg -> optReg.orElseThrow();

	public static Supplier<String> getRegion = () -> {
		HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
				.filter(ServletRequestAttributes.class::isInstance).map(ServletRequestAttributes.class::cast)
				.map(ServletRequestAttributes::getRequest).orElse(null);
		Optional<String> region = Arrays.stream(request.getCookies()).filter(c -> "region".equals(c.getName()))
				.map(Cookie::getValue).findAny();
		return validateRegion.apply(region);
	};

}
