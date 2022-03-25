//package com.example.pieceart.security.filter;
//
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////@WebFilter(urlPatterns = "/api/login")
//public class WrapperFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
//        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
//
//        chain.doFilter(httpServletRequest, httpServletResponse);
//
//    }
//}
