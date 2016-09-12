package com.thoughtmechanix.zuulsvr.filters;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationFilter extends ZuulFilter {
    private static final int FILTER_ORDER =  2;
    private static final boolean  SHOULD_FILTER=true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return filterUtils.PRE_FILTER_TYPE;
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
        if (filterUtils.getAuthToken() !=null){
            return true;
        }

        return false;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        filterUtils.flog("Entering the authentication filter");

        if (isAuthTokenPresent()){
           filterUtils.flog("Authentication token is present.");
            return null;
        }

        filterUtils.flog("Authentication token is not present.");

        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        return null;

    }
}
