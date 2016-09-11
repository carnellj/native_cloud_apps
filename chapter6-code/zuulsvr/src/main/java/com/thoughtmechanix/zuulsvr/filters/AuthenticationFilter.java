package com.thoughtmechanix.zuulsvr.filters;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationFilter extends ZuulFilter {
    private static final String FILTER_TYPE = "pre";
    private static final int FILTER_ORDER =  2;
    private static final boolean  SHOULD_FILTER=true;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter() {
    }

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthTokenPresent() {

        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader("tmx-auth-token") !=null){
            return true;
        }

        return false;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String correlationId = ctx.getZuulRequestHeaders().get("tmx-correlation-id");
        logger.debug("Entering the authorization filter");

        if (isAuthTokenPresent()){
            logger.debug(">>>Authentication token is present. Correlation id: {}", correlationId);
            return null;
        }

        logger.debug(">>>Authentication token is not present. Correlation id: {}", correlationId);
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        return null;

    }
}
