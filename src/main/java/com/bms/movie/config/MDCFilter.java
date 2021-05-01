package com.bms.movie.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MDCFilter implements Filter {
	
	private final Tracer tracer;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
        	MDC.put("trace-id", tracer.activeSpan().context().toTraceId());
    		MDC.put("span-id", tracer.activeSpan().context().toSpanId());
            // Setup MDC data:
            String mdcData = String.format("[userId:%s | requestId:%s] ", "hello", "hello");
            MDC.put("mdcData", mdcData); //Variable 'mdcData' is referenced in Spring Boot's logging.pattern.level property
            chain.doFilter(request, response);
        } finally {
           // Tear down MDC data:
           // ( Important! Cleans up the ThreadLocal data again )
            MDC.clear();
        }
    }
}