package com.thoughtmechanix.organization.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        UserContext.flog("Entering the UserContextFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID);
        String userId = httpServletRequest.getHeader(UserContext.USER_ID);
        String authToken = httpServletRequest.getHeader(UserContext.AUTH_TOKEN);
        String orgId = httpServletRequest.getHeader(UserContext.ORG_ID);


        UserContext.setCorrelationId(correlationId);
        UserContext.setUserId(userId);
        UserContext.setAuthToken(authToken);
        UserContext.setOrgId(orgId);

        UserContext.flog("Exiting the UserContextFilter");
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}