package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.context.RequestContext;
import org.fluentd.logger.FluentLogger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class FilterUtils {

    private static FluentLogger FLOG = FluentLogger.getLogger("tmx", "fluentd", 24224);

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "routing";

    private FluentLogger getFLOG() {
        return FLOG;
    }

    public String getCorrelationId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.getRequest().getHeader(CORRELATION_ID) !=null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public  final String getOrgId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getZuulRequestHeaders().get(ORG_ID);
    }

    public static void setOrgId(String orgId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID,  orgId);
    }

    public final String getUserId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(USER_ID);
    }

    public void setUserId(String userId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID,  userId);
    }

    public final String getAuthToken(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN);
    }

    public void flog(String message){

        Map<String, Object> data = new HashMap<String, Object>();
        String log ="{'serviceName':'%s'," +
                "'correlationId':'%s'," +
                "'organizationId':'%s'," +
                "'userId':'%s'" +
                "'message':'%s'}";

        String msg = String.format(log,getCorrelationId(),
                "ZUUL",
                getOrgId(),
                getUserId(),
                message);
        data.put("tmxdata",msg );
        FLOG.log("tmx",data);
    }

}
