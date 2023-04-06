package com.example.eshop_backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TestFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("is after request" + request + " response :" + response + " filterChain :" + filterChain);
        response.setHeader("Hallo","Na du");
        filterChain.doFilter(request,response);
    }
}
