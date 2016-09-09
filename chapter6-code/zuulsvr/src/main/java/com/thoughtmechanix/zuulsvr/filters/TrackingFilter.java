package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/*
     There are three types of filters in Zuul
     Pre filters (This get get called first)  "pre"
     Route filters (These get call second)    "routing"
     Post Filter   (This get called last)     "post"
 */
public class TrackingFilter extends ZuulFilter{
    private static final String FILTER_TYPE = "pre";
    private static final int FILTER_ORDER =  1;
    private static final boolean  SHOULD_FILTER=true;

    public TrackingFilter(){

    }

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent(){
      RequestContext ctx = RequestContext.getCurrentContext();
      if (ctx.getRequest().getHeader("tmx-correlation-id") !=null){
          return true;
      }

      return false;
    }

    private String getCorrelationIdFromHeader(){
       RequestContext ctx = RequestContext.getCurrentContext();
       if (ctx.getRequest().getHeader("tmx-correlation-id") !=null) {
           return ctx.getRequest().getHeader("tmx-correlation-id");
       }
       else{
         return  ctx.getZuulRequestHeaders().get("tmx-correlation-id");
       }

    }

    private void setCorrelationId(String correlationId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("tmx-correlation-id", correlationId);
    }


    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    public Object run() {

        if (isCorrelationIdPresent()){
            System.out.printf("!!!!!!> Correlation-id found: %s. ", getCorrelationIdFromHeader());
        }
        else{
            setCorrelationId( generateCorrelationId() );
            System.out.printf("!!!!!!> Correlation-id generated: %s.", getCorrelationIdFromHeader());
        }
        return null;
    }
}