package com.example.githubapi.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Component
@Order(1)
public class RequestResponseLoggingFilter extends GenericFilterBean {
        @Override
        public void doFilter(
                ServletRequest request,
                ServletResponse response,
                FilterChain chain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            if (!"application/json".equals(req.getHeader("Accept"))) {
                res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                res.getWriter().write("{\"status\": 406, \"message\": \"Not acceptable\"}");
                return;
            }

            chain.doFilter(request, response);

            if (res.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                res.getWriter().write("{\"status\": 404, \"message\": \"Resource not found\"}");
                return;
            }
        }
    }

