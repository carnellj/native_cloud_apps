package com.thoughtmechanix.licenses.utils;

import org.fluentd.logger.FluentLogger;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";

    private static final ThreadLocal<String> correlationId= new ThreadLocal<String>();
    private static final ThreadLocal<String> authToken= new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<String>();


    public static String getCorrelationId() { return correlationId.get(); }
    public static void setCorrelationId(String cid) {correlationId.set(cid);}

    public static String getAuthToken() { return authToken.get(); }
    public static void setAuthToken(String aToken) {authToken.set(aToken);}

    public static String getUserId() { return userId.get(); }
    public static void setUserId(String aUser) {userId.set(aUser);}

    public static String getOrgId() { return orgId.get(); }
    public static void setOrgId(String aOrg) {userId.set(aOrg);}

    private static FluentLogger FLOG = FluentLogger.getLogger("tmx", "fluentd", 24224);

    public static final void flog(String message){
        Map<String, Object> data = new HashMap<String, Object>();
        String log = "{'serviceName':'%s'," +
                "'correlationId':'%s'," +
                "'organizationId':'%s'," +
                "'userId':'%s'" +
                "'message':'%s'}";

        String msg = String.format(log,
                                   "LICENSING",
                                   getCorrelationId(),
                                   getOrgId(),
                                   getUserId(),
                                   message);
        data.put("tmxdata",msg );
        FLOG.log("tmx",data);
    }

    public static HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CORRELATION_ID, getCorrelationId());

        return httpHeaders;
    }
}