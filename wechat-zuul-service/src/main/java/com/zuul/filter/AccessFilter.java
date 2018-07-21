package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Value("${access.token}")
    private String token;

    @Override
    public Object run() {
        //todo 设计token怎么生成,过滤其他请求
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        String accessToken = request.getHeader("accessToken");
        if (StringUtils.isEmpty(accessToken) || !token.equals(accessToken)){
            logger.info("access token incorrect...reject request");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        logger.info("access token ok...");
        return null;
    }

    /**
     * 请求路由之前执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 该过滤器是否执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
