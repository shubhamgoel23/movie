package com.bms.movie.config;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bms.movie.context.RegionContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionInterceptor implements HandlerInterceptor {

	public static final String REGION_COOKIE = "region";
	public static final String DEFAULT_REGION = "GL";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Optional<String> regOps = Arrays.stream(request.getCookies())
				.filter(c -> REGION_COOKIE.equalsIgnoreCase(c.getName())).map(Cookie::getValue).findAny();
		regOps.ifPresentOrElse(RegionContext::setRegion, () -> RegionContext.setRegion(DEFAULT_REGION));
		log.info("Region context: {}", RegionContext.getRegion());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.info("Cleared Region Context");
		RegionContext.clear();
	}

}
